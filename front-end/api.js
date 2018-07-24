var firstId = 0;
var currId = firstId;

var request = {
  post: function (data, url, successCallback, failureCallback)
  {
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
    .then(
        function(data) {
          console.log("DATA = ", data);
          if (data.status == 201) {
            successCallback(currId, data.title);
          }
          else if (data.status == 304) {
          }
        },
        function(error) {
          console.log("ERROR = ", error);
        });

   }
  }

var redirect = { redirectToPage: function (url) {
    $(location).attr('href', url)
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
      method: 'POST'
    })
    .then(response => {
      console.log(response)          
      //return response;
      if(response.status==201){
        redirect.redirectToPage("index.html");
      }else if(response.status==204){
        alert('User already exists');
      }else{
        alert('A problem occured, Please try again later.');
      }
    })
}
}


