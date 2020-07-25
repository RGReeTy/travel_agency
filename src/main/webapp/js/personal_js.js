//Personal account editing methods start

async function showEditableData() {
    $('#button_edit').fadeOut();
    $('.new_input').fadeIn();
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


function cancelEditData() {
    $('.new_input').fadeOut();
    $('#applyEditData').fadeOut();
    $('#cancelEditData').fadeOut();
    $('#button_edit').fadeIn();
}

//end---------------------------------------------

//Personal tour info
async function showTourInfo(id_tour) {
    var temp_id = id_tour;
    $.ajax({
        type: 'POST',
        url: "/travel_agency_war/ajax",
        data: "command=SHOW_SINGLE_TOUR&id_tour=" + id_tour,
        dataType: "json",
        success: function (data) {
            console.log(data);
            $(".values").find("td").remove();
            $('#title').append(data.tour.title);
            $('#idTourInt').append(temp_id);
            $('#dateStart').append(data.tour.dateStart.day + ':' + data.tour.dateStart.month + ':' + data.tour.dateStart.year);
            $('#dateEnd').append(data.tour.dateEnd.day + ':' + data.tour.dateEnd.month + ':' + data.tour.dateEnd.year);
            $('#numberOfPlaces').append(data.tour.numberOfPlaces);
            $('#hotel_title').append(data.tour.hotel.title);
            $('#price').append(data.tour.price);
            $('#personal_count').append(data.personal_count);
            $('#description').append(data.tour.description);
            $('#image_from_db').prepend($('<img>', {id: 'theImg', src: data.tour.urlWallpaper}));
            $('#personal_tour').fadeIn();
            $('#collection_of_tours').fadeOut();
            $('body').scrollTo('#title');
            $('html, body').animate({
                scroll: $("#title").offset().top
            }, 2000);

            //scrollTop: targetOffset}, 1000);
        },
        error: function (e) {
            alert("Cant load data!");
        }
    });
}

async function sendContactFromAnonim() {
    let sendForm = new FormData();
    let name = $("#name").val();
    let phone = $("#phone").val();
    let id_tour = $('#idTourInt').text();
    if ((name.length === 0) & (phone.length === 0)) {
        alert("All fields are empty!");
    } else {
        console.log(name + "===" + phone + "===" + id_tour);
        sendForm.append("command", "CREATE_NEW_DEFRAYAL_FROM_ANONIM");
        sendForm.append("name", name);
        sendForm.append("phone", phone);
        sendForm.append("id_tour", id_tour);
        let response = await fetch("/travel_agency_war/ajax", {
            method: 'POST',
            body: sendForm,
        });

        if (response.ok) {
            $('#contact_field').fadeOut();
            $('#congrats').fadeIn();
        } else {
            alert("Something goes wrong!");
            console.log(response);
        }
    }
}

async function sendConfirming() {
    let sendForm = new FormData();
    let id_tour = $('#idTourInt').text();

    sendForm.append("command", "CREATE_NEW_DEFRAYAL_FROM_USER");
    sendForm.append("id_tour", id_tour);
    let response = await fetch("/travel_agency_war/ajax", {
        method: 'POST',
        body: sendForm,
    });

    if (response.ok) {
        $('#send').fadeOut();
        $('#congrats').fadeIn();
    } else {
        alert("Something goes wrong!");
        console.log(response);
    }
}


//Confirming the Payment
async function confirmThePayment() {
    let sendConfirming = new FormData();
    let defrayalID = $('#defrayalID').text();
    let defrayalNote = $('#defrayalNote').text();

    sendConfirming.append("command", "CONFIRM_THE_PAYMENT");
    sendConfirming.append("defrayalID", defrayalID);
    sendConfirming.append("defrayalNote", defrayalNote);
    let response = await fetch("/travel_agency_war/ajax", {
        method: 'POST',
        body: sendConfirming,
    });

    if (response.ok) {
        // $('#payment').delete();
        $('#payment').text("100%");
        $('#defrayalNote').text("Confirmed");
        $('#confirmPayment').fadeOut();
    } else {
        alert("Something goes wrong!");
        console.log(response);
    }
}

