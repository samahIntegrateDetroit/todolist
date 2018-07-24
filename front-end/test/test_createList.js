
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
QUnit.test("User input entered populates list title on card after button click", function(assert) {

  var done = assert.async();
  card.called = false;
  console.log("TOP");
  currId = 9909;
  var listTitleEntered = "New List";
  const input = document.querySelector('#titleInput');
  input.value = listTitleEntered;
  console.log("before promise");

  var promise = new Promise(function(resolve, reject) {
      resolve({"status": 201, "title": listTitleEntered});
  });
  console.log("after promise");

  let spy = sinon.spy(card, "createListCard");
  let stub = sinon.stub(window, 'fetch').returns(promise);

  console.log("after stub");

  document.querySelector('#createListButton').click();

  console.log("after click");

  setTimeout(function() {
    equal(spy.called, true);
    equal(spy.calledWithExactly(9909, listTitleEntered), true);
    currId = 0;
    stub.restore();
    spy.restore();
    done();
  });
});

QUnit.test("list title field initially 'Enter title here' for a new card after other cards have been added", function(){
  const input = document.querySelector('#titleInput');

  equal(input.placeholder, "Enter title here", "Initial list title placeholder is presents");
});

QUnit.test("Card was not created with 304 status", function(assert) {
  var done = assert.async();
  var listTitleEntered = "New List";
  const input = document.querySelector('#titleInput');
  input.value = listTitleEntered;

  var promise = new Promise(function(resolve, reject) {
      resolve({"status": 304, "title": listTitleEntered});
  });
  
  let spy = sinon.spy(card, "createListCard");
  let stub = sinon.stub(window, 'fetch').returns(promise);
  
  document.querySelector('#createListButton').click();

  setTimeout(function() {
    equal(spy.called, false);
    stub.restore();
    spy.restore();
    done();
  });

});
