
QUnit.test("list title field initially 'Enter title here'", function(){
  const input = document.querySelector('#titleInput');

  equal(input.placeholder, "Enter title here", "Initial list title placeholder is presents");
});

QUnit.test("button click sends information to DB", function() {
  var spy = sinon.spy(request, "post");
  const input = document.querySelector('#titleInput');

  var expectedTitle = "this is new title";
  input.value = expectedTitle;
  var button = document.querySelector('#createListButton').click();
  equal(spy.args[0][0].title, expectedTitle, "title was as expected");
  equal(spy.args[0][1], "http://localhost:8080/list");
  spy.restore();
});

QUnit.test("Create button populates list title with user input from db", function() {

// WIP trying to figure out how to test this.
  var listInfo = { httpStatus: "201", titleEntered: expectedTitle };

  var postStub = sinon.stub( request, "post" ).returns( listInfo );

  const input = document.querySelector('#titleInput');
  var expectedTitle = "this is the updated title";
  input.value = expectedTitle;

  var button = document.querySelector('#createListButton').click();
  var listItemId = "#list" + (currId - 1);
  var actualTitle = document.querySelector(listItemId).innerHTML;
  equal( actualTitle, expectedTitle, "Title was updated on new card" );

});

QUnit.test("list title field initially 'Enter title here' for a new card after other cards have been added", function(){
  const input = document.querySelector('#titleInput');

  equal(input.placeholder, "Enter title here", "Initial list title placeholder is presents");
});

QUnit.test("Status 304 is present on unsuccessful list creation", function() {
  var listInfo = { httpStatus: "304", titleEntered: expectedTitle };

  var postStub = sinon.stub( request, "post" ).returns( listInfo );

  const input = document.querySelector('#titleInput');
  var expectedTitle = "this is the updated title";
  input.value = expectedTitle;

  var button = document.querySelector('#createListButton').click();
  var listItemId = "#list" + (currId - 1);
  var actualTitle = document.querySelector(listItemId).innerHTML;
  equal( actualTitle, expectedTitle, "Title was updated on new card" );

});