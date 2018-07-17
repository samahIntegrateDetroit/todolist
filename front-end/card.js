"use strict";
var card = 
{
  createListCard : function (newCardId, newTitle) {

    let todoCard = `
    <div class="grid-x grid-padding-x">
    <div class="large-6 large-pull-6 cell">
    
        <div class="card card-tabs large-6">
    
            <div class="card-divider">
              <h6 id="list${newCardId}" class="listCardTitle"> ${newTitle} </h6>
    
              <ul class="dropdown menu" data-dropdown-menu>
              <li class="is-dropdown-submenu-parent">
                  <a href="#">
                      <img src="./resources/iconmonstr-gear-1.svg">
                  </a>
                <ul class="menu">
                  <li><a href="./index.html">Active Lists</a></li>
                  <li><a href="./archive.html">Archived Lists</a></li>
                  <li><a href="#">Collaborations</a></li>
                  <li><a href="#">Log-In / Create Account</a></li>
                </ul>
                </li>
                </ul>
            
            </div>
        
            <div class="tabs-content" data-tabs-content="collapsing-tabs">
        
              <div class="tabs-panel is-active" id="panel1c">
                <div class="card-section">
                  <h4>Task A</h4>
                  <p>Due date goes here</p>
                </div>
              </div>
        
              <div class="tabs-panel" id="panel2c">
                <div class="card-section">
                  <h4>Task B</h4>
                  <p>It has an easy to override visual style, and has a cat on the bottom.</p>
                </div>
                </div>
        
              <div class="tabs-panel" id="panel3c">
                <div class="card-section">
                  <h4>Task C</h4>
                  <p>It has an easy to override visual style, it has an image in the card section.</p>
                </div>
              </div>
        
            </div>
        </div>
        </div>`

    $("body").append(todoCard);
    currId++;
  }
  }
