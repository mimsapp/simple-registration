let mobileNumberOk = false;
let emailOk = false;
let firstNameOk = false;
let lastNameOk = false;

function redirectLogin() {

    window.location.href = 'login';
}

function addDobOption() {

    let optionDays = '<option value=0>Day</option>';
    for(var i = 1; i <= 31; ++i) {
        optionDays += '<option value='+i+'>'+i+'</option>';
    }

     $('#day').html(optionDays);

    let optionYears = '<option value=0>Year</option>';
    for(var i = 1900; i <= 2019; ++i) {
        optionYears += '<option value='+i+'>'+i+'</option>';
    }

    $('#year').html(optionYears);
}

function checkMobileNumber() {

    $('#mobileNumberError').text('');

    let regex = '^(08)(1|2|3|5|7|8|9)(([0-9]{7})|([0-9]{9}))$';
    let mobileNumber = $('#mobileNumber').val();

    if(mobileNumber.match(regex)) {
        $('#mobileNumberForm').addClass('form-input-loading');
        $.get( "api/checkMobileNumberExistence?mobileNumber="+mobileNumber, function( data ) {
             $('#mobileNumberForm').removeClass('form-input-loading');
             if(data.statusCode == 0) {
                if(!data.payload) {
                    mobileNumberOk = true;
                } else {
                    $('#mobileNumberError').text('Mobile number is already registered');
                }
             }
        });
    } else {
        $('#mobileNumberError').text('Mobile number is not a valid indonesian number');
    }
}

function checkEmail() {

 $('#emailError').text('');

    let email = $('#email').val();

    if(/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
         $('#emailForm').addClass('form-input-loading');
        $.get( "api/checkEmailExistence?email="+email, function( data ) {
            $('#emailForm').removeClass('form-input-loading');
            if(data.statusCode == 0) {
               if(!data.payload) {
                   emailOk = true;
               } else {
                   $('#emailError').text('Email is already registered');
               }
            }
        });
    } else {
        $('#emailError').text('Email is not valid address');
    }
}

function register() {

    if($('#mobileNumber').val()) {
        mobileNumberOk = true;
    } else {
        $('#mobileNumberError').text('Mobile number is required');
    }
    if($('#email').val()) {
        emailOk = true;
    } else {
        $('#emailError').text('Email is required');
    }
    if($('#firstName').val()) {
        firstNameOk = true;
        $('#firstNameError').text('');
    } else {
        $('#firstNameError').text('First name is required');
    }
    if($('#lastName').val()) {
        lastNameOk = true;
        $('#lastNameError').text('');
    } else {
        $('#lastNameError').text('Last Name is required');
    }

    let dob = '';

    if($('#month').val() != 0 && $('#day').val() != 0 && $('#year').val() != 0) {
        dob += $('#day').val() < 10 ? '0'+$('#day').val() : $('#day').val() +'-';
        dob += $('#month').val() < 10 ? '0'+$('#month').val() : $('#month').val() + '-';
        dob += $('#year').val();
    }

    if(mobileNumberOk && emailOk && firstNameOk && lastNameOk) {
        let data = {};
        data.mobileNumber = $('#mobileNumber').val(),
        data.firstName = $('#firstName').val(),
        data.lastName = $('#lastName').val(),
        data.dateOfBirth = dob,
        data.gender = $("input[name='gender']:checked").val();
        data.email= $('#email').val()

        $.ajax({
            type: 'POST',
            url: 'api/register',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                if(result.statusCode == 0) {
                    $('#register').prop('disabled',true).css('opacity',0.5);
                    $('#mobileNumber').prop('disabled', 'disabled');
                    $('#firstName').prop('disabled', 'disabled');
                    $('#lastName').prop('disabled', 'disabled');
                    $('#email').prop('disabled', 'disabled');
                    $('#male').prop('disabled', 'disabled');
                    $('#female').prop('disabled', 'disabled');
                    $('#month').attr('disabled', 'disabled');
                    $('#day').attr('disabled', 'disabled');
                    $('#year').attr('disabled', 'disabled');
                    $('#loginSubmit').removeClass('form-input-hidden');
                    $('#loginSubmit').addClass('form-input');
                    $('#register').prop('onclick', null).off('click');
                }
            }
        });
    }
}