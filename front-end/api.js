var firstId = 0;
var currId = firstId;
// var httpStatus;
// var getHttpStatus(){
//   return request.body.;
// }
var request = { post: function (data, url, successCallback, failureCallback){
  
  return fetch(
    url,
    {
      headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
      },
      body: JSON.stringify(data),
      method: 'POST', })
      .then(data => {
        // if (data.status == 201) {
          
        //   } else {
        //   failureCallback("sorry")
        // }
        //var listInfo = {"httpStatus": requestStatus, "updatedTitle": updatedTitle }
        // var requestStatus = data.status;
        data.json()
        .then(jsonData => {
          var updatedTitle = jsonData.title
          if (data.status == 201) {
            successCallback(currId, updatedTitle)
          }
        })
      })
}}

var userrequest = { post: function (data, url){

  fetch(
  url,
  {
    headers: { 
      'Accept': 'application/json',
      'Content-Type': 'application/json' 
    },
    body: JSON.stringify(data),
    method: 'POST', })
    .then(response => {
          console.log(response)          
          return response;
        })
}}
