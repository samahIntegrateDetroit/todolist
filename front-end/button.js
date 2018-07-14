const buttonHandler = () => {
  var listTitle = document.querySelector("#titleInput").value
  request.post({title: listTitle}, "http://localhost:8080/list")

  createListCard(currId, listTitle);
  initialize()
}

window.onload = () => {
  initialize()
  const submitButton = document.querySelector("#createListButton")
  submitButton.addEventListener('click', buttonHandler)
  const input = document.querySelector('#titleInput');
}

function initialize(){
  var titleInput = document.querySelector("#titleInput")
  titleInput.placeholder = "Enter title here"
  titleInput.value = "";
}