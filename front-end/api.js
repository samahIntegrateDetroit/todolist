var request = { post: function (data, url){
  fetch(
  url,
  {
    headers: { 
      'Accept': 'application/json',
      'Content-Type': 'application/json' 
    },
    body: JSON.stringify(data),
    method: 'POST',
  }).then(data=>{
  });
}}