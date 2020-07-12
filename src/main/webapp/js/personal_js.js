async function showEditableData() {
    $('#button_edit').fadeOut();
    $('.new_input').fadeIn();
    // $('.editable').append('<td><input type="text" placeholder="New value" class="new_input"/></td>');
    $('#applyEditData').fadeIn();
    $('#cancelEditData').fadeIn();
}

//Send input data to db
async function applyEditData() {
    let sendForm = new FormData();
    let firstname = $("#firstname").val();
    let lastname = $("#lastname").val();
    let email = $("#email").val();
    let phone = $("#phone").val();
    if ((firstname.length === 0) & (lastname.length === 0) & (email.length === 0) & (phone.length === 0)) {
        alert("All fields are empty!");
    }
    sendForm.append("command", "UPDATE_USER_PROFILE");
    sendForm.append("firstname", firstname);
    sendForm.append("lastname", lastname);
    sendForm.append("email", email);
    sendForm.append("phone", phone);
    let response = await fetch("/travel_agency_war/ajax", {
        method: 'POST',
        body: sendForm,
    });

    if (response.ok) {
        console.log(firstname + " = " + lastname + " = " + email + " = " + phone);
        if (firstname.length !== 0) {
            $("#firstname_val").text(firstname);
        }
        if (lastname.length !== 0) {
            $("#lastname_val").text(lastname);
        }
        if (email.length !== 0) {
            $("#email_val").text(email);
        }
        if (phone.length !== 0) {
            $("#phone_val").text(phone);
        }
    } else {
        alert("Something goes wrong!");
        console.log(response);
    }
    cancelEditData();
};


//Built table by data from db
$(document).ready(function () {
    $("#btnSubmit").click(function (e) {
        e.preventDefault();
        $("#show2").find("tr").remove();
        $("#show2").find("tbody").remove();
        $("#show2").find("thead").remove();
        $.ajax({
            type: 'POST',
            url: '/RoomsWithLightBulbs/ajax?command=ALL_ROOMS',
            dataType: "json",
            success: function (data) {
                let trHTML = '<thead><tr>' +
                    '            <th>Title</th>\t' +
                    '            <th>Country</th>\t' +
                    '            <th>Light is</th>\t' +
                    '            <th>Button</th>\t' +
                    '        </tr></thead><tbody>';
                for (let i in data.roomList) {
                    trHTML += '<tr class=\"temp\"><td class=\"roomsName\">' + data.roomList[i].roomsName +
                        '\t</td><td class=\"country\">' + data.roomList[i].country +
                        '\t</td><td class=\"lightStatus\">' + data.roomList[i].lightStatus +
                        '\t</td><td><button type=\"submit\" id=\"showRoom\" class=\"showRoom\"' +
                        '>Enter</button>' +
                        '\t</td></tr>';
                }
                trHTML += '</tbody>';
                $("#show2").append(trHTML);
            },
            error: function (e) {
                $("#show2").html(e.responseText);
            }
        });
    });
});


//Function for loading countries from by ajax
let working = false;

async function loadAllCountries() {
    if (working) {
        return false;
    }
    working = true;
    let dataToGetCountries = new FormData();
    dataToGetCountries.append("command", "ALL_COUNTRIES");
    let response = await fetch("/RoomsWithLightBulbs/ajax", {
        method: 'POST',
        body: dataToGetCountries,
    });

    if (response.ok) {
        let json = await response.json();
        const $select = $("#dropdownlist");
        $select.find("option").remove();
        for (let i in json.country) {
            $("<option>").val(json.country[i]).text(json.country[i]).appendTo($select);
        }
    } else {
        alert("ERROR: " + response.status);
    }
}

function cancelEditData() {
    $('.new_input').fadeOut();
    $('#applyEditData').fadeOut();
    $('#cancelEditData').fadeOut();
    $('#button_edit').fadeIn();
}