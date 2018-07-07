var request = { post: function (data, url){

  fetch(
  url,
  {
    headers: { 
      'Accept': 'application/json',
      'Content-Type': 'application/json' 
    },
    body: JSON.stringify(data),
    method: 'POST', })
    .then(data => {
        var requestStatus = data.status
        data.json().then(jsonData => {
          var updatedTitle = jsonData.title
          var listInfo = {"httpStatus": requestStatus, "updatedTitle": updatedTitle }
          console.log(listInfo)          
          return listInfo
        })
    })
}}