
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
QUnit.test("User input entered populates list title on card after button click", function() {
  var listTitleEntered = "New List";
  const input = document.querySelector('#titleInput');
  input.value = listTitleEntered;

  var promise = new Promise(function(resolve, reject) {
      resolve({"status": 201, "title": listTitleEntered});
  });
  
  let spy = sinon.spy(card, "createListCard");
  let stub = sinon.stub(window, 'fetch').returns(promise);
  
  document.querySelector('#createListButton').click();
 
  equal(spy.calledOnce, true);
  stub.restore();
  spy.restore();
});

QUnit.test("list title field initially 'Enter title here' for a new card after other cards have been added", function(){
  const input = document.querySelector('#titleInput');

  equal(input.placeholder, "Enter title here", "Initial list title placeholder is presents");
});

QUnit.test("Card was not created with 304 status", function() {
  var listTitleEntered = "New List";
  const input = document.querySelector('#titleInput');
  input.value = listTitleEntered;

  var promise = new Promise(function(resolve, reject) {
      resolve({"status": 304, "title": listTitleEntered});
  });
  
  let spy = sinon.spy(card, "createListCard");
  let stub = sinon.stub(window, 'fetch').returns(promise);
  
  document.querySelector('#createListButton').click();
  
  equal(spy.called, false);
  stub.restore();
});