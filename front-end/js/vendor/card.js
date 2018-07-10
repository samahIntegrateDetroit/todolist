"use strict";
{
    $("#createListButton").on("click", (event) =>{
        $("body").append(todoCard);
    });

    let todoCard = `

    <div class="grid-x grid-padding-x">
    <div class="large-6 large-pull-6 cell">

        <div class="card card-tabs large-6">

            <div class="card-divider">
              <center><h6 class="listCardTitle">Title</h6></center>
             </div>
        
             <div class="tabs-content" data-tabs-content="collapsing-tabs">
        
              <div class="tabs-panel is-active" id="panel1c">
                <img src="http://lorempixel.com/485/248/cats/7/">
                <div class="card-section">
                  <h4>This is a card.</h4>
                  <p>It has an easy to override visual style, and is appropriately subdued.</p>
                </div>
              </div>
        
              <div class="tabs-panel" id="panel2c">
                <div class="card-section">
                  <h4>This is a card.</h4>
                  <p>It has an easy to override visual style, and has a cat on the bottom.</p>
                </div>
                <img src="http://lorempixel.com/485/248/cats/5/">
              </div>
        
              <div class="tabs-panel" id="panel3c">
                <div class="card-section">
                  <h4>This is a card.</h4>
                  <img src="http://lorempixel.com/485/248/cats/6/">
                  <p>It has an easy to override visual style, it has an image in the card section.</p>
                </div>
              </div>
        
            </div>
        </div>
    </div>`
}
