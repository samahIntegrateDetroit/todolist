
QUnit.test("button click sends information to DB", function() {
  var spy;

  spy = sinon.spy(request, "post");
  const input = document.querySelector('#titleInput');
  console.log(input)

  var expectedTitle = "this is new title"
  document.querySelector("#titleInput").value = expectedTitle
  var button = document.querySelector('#createListButton').click();
  equal(spy.args[0][0].title, expectedTitle, "title was as expected")
  equal(spy.args[0][1], "http://localhost:8080/list")
  spy.restore();

});