
QUnit.test("list title field initially 'Enter title here'", function() {
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

/* ------------- WIP test failing after api.js code refactor.  -------------- */
QUnit.test("Create button populates list title with user input from db", function() {
  // var listInfo = { httpStatus: "201", titleEntered: expectedTitle };
  var listTitleEntered = "New List"
  const input = document.querySelector('#titleInput');
  var expectedTitle = "totally different string";
  input.value = expectedTitle;

  var button = document.querySelector('#createListButton').click();
  var actualTitle = document.querySelector(listItemId).innerHTML;
  equal( actualTitle, expectedTitle, "Title was updated on new card" );
  postStub.restore();
});

QUnit.test("list title field initially 'Enter title here' for a new card after other cards have been added", function(){
  const input = document.querySelector('#titleInput');

  equal(input.placeholder, "Enter title here", "Initial list title placeholder is presents");
});

/* ------------- WIP test failing with 3rd test -------------- */
QUnit.test("Status 304 is present on unsuccessful list creation", function() {
  var idBefore = currId;
  var enteredTitle = "List title";
  var listInfo = { httpStatus: "304", titleEntered: enteredTitle };

  // var postStub = sinon.stub( request, "post" ).returns( listInfo );

  const input = document.querySelector('#titleInput');
  input.value = enteredTitle;
  var button = document.querySelector('#createListButton').click();
  var listItemId = "#list" + (currId - 1);
  // console.log(listInfo)
  var actualTitle = document.querySelector(listItemId).innerHTML;
  equal( 304, listInfo.httpStatus, "Found 304 status, title not updated" );
  equal( idBefore, currId, "A new card was not added");
  
});