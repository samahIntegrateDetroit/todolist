
QUnit.test("list title field initially 'Enter title here'", function(){
  const input = document.querySelector('#titleInput');
  console.log(input)
  equal(input.value, "Enter title here", "Initial list text is prompt");
});