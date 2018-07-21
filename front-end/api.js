// var firstId = 0;
// var currId = firstId;
// var httpStatus;
// var getHttpStatus(){
//   return request.body.;
// }
// var request = { post: function (data, url, successCallback, failureCallback){
  
//   return fetch(
//     url,
//     {
//       headers: { 
//         'Accept': 'application/json',
//         'Content-Type': 'application/json' 
//       },
//       body: JSON.stringify(data),
//       method: 'POST'
//     }).then( data => data.json())
//       .then(jsonData => {
//         var updatedTitle = jsonData.title
//         console.log("outside If")
//         if (data.status == 201) {
//           console.log("inside If")
//           successCallback(currId, updatedTitle)
//         }
//     }).catch(console.log)
// }}

var userrequest = { post: function (data, url){

  fetch(
    url,
    {
      headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
      },
      body: JSON.stringify(data),
      method: 'POST'
    })
    .then(response => {
      console.log(response)          
      //return response;
      if(response.status==201){
        userRedirect.redirectToIndex();
      }else{
        alert('bad response status');
      }
    })
}
}

var userRedirect = { redirectToIndex: function() {
  $(location).attr('href', 'index.html')
}}
