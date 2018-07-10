
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

// WIP trying to figure out how to test this.
  var listInfo = {httpStatus = "201", updatedTitle = "YO"};
  document.querySelector("#titleInput").value = updatedTitle

  var button = document.querySelector('#createListButton').click();
  var stub = sinon.stub(request, "post").returns(
     listInfo
  )
  var newTitle = document.querySelector('.listCardTitle').value
  equal(updatedTitle, newTitle,"Title was updated on new card")

});

