function createInput() {
    return $('<input id="userListTitle" type="text" value="List title entered here"/>')
      .appendTo('#qunit-fixture');
  }
  
  module("Writing and reading to input", {})
  
  test("writes to value", function(assert) {
    var $input = createInput();
    var result = "List title returned from database";

    $input.val(result);
  
    strictEqual($input.val(), result);
  });
  
  test("reads from value", function(assert) {
    var $input = createInput();
    
    $input.val();
  
    strictEqual($input.val(), "List title entered here");

  });