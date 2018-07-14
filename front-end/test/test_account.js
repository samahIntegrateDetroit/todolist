// const Foundation = require('foundation-sites');

QUnit.test("Missmatched passwords disables submit button", function(){
    document.getElementById('passwordOne').value = "testpass1";
    document.getElementById('passwordTwo').value = "testpass2";
    
    validate_passwords();
    var submit_button_state = document.getElementById( "sign-up-button" ).disabled;
    equal(submit_button_state, true, "Validated Submit Button is disabled");
  });

  QUnit.test("Missmatched passwords pops up tool tip", function(){
    document.getElementById('passwordOne').value = "testpass1";
    document.getElementById('passwordTwo').value = "testpass2";
    validate_passwords();
    var foundation_tooltip_id = "#"+$('#passwordTwo').attr('data-yeti-box');
    var pass_tooltip_status = $(foundation_tooltip_id).attr('data-is-active');
    
    equal(pass_tooltip_status, "true", "Validated Password Tooltip is visible");
  });