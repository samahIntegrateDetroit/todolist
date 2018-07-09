
QUnit.test("list title field initially 'Enter title here'", function(){
  const input = document.querySelector('#titleInput');

  equal(input.placeholder, "Enter title here", "Initial list title placeholder is presents");
});

QUnit.test("button click sends information to DB", function() {
  var spy = sinon.spy(request, "post");
  const input = document.querySelector('#titleInput');

  var expectedTitle = "this is new title"
  document.querySelector("#titleInput").value = expectedTitle
  var button = document.querySelector('#createListButton').click();
  equal(spy.args[0][0].title, expectedTitle, "title was as expected")
  equal(spy.args[0][1], "http://localhost:8080/list")
  spy.restore();
});

QUnit.test("Create button populates list title with user input from db", function() {
  var stub = sinon.stub(request, "post").returns()
});

