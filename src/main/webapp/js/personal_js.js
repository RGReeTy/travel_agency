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