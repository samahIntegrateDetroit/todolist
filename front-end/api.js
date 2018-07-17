var firstId = 0;
var currId = firstId;
// var httpStatus;
// var getHttpStatus(){
//   return request.body.;
// }
var request = { post: function (data, url, successCallback){
  
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
        if (data.status == 201) {
          console.log(data);
          data.json().then(jsonData => {
          console.log(jsonData);
            var updatedTitle = jsonData.title;
            successCallback(currId, updatedTitle)
          })
        }
        // var listInfo = {"httpStatus": requestStatus, "updatedTitle": updatedTitle }
        // var requestStatus = data.status;
      })
}}
