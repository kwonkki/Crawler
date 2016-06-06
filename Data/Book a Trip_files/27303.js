//Developer:Chris Lane
//Fix for pattern_id=27303
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27303
//
(function(){
    'use strict';
    $(document).ready(function () {
       
       //Get the advisory text and remove the old image
       var advisoryText =  $('#advisoryimg').attr('alt');
       $('#advisoryimg').remove();
       
       //put the old image in the background
       $('#advisorybox').css('background-image','url("http://210.213.139.203/Advisory%20Setting/5J_NO_YQ_Pop_Up_study3.png")');
       $('#advisorybox').css('height','100%');
       $('#advisorybox').css('width','100%');
       $('#advisorybox').css('background-repeat','no-repeat');
       $('#advisorybox').css('left','220px')
       
       //Now add back the advisory text in a DIV. 
       //Style and position it over the text in the background image
       $('#advisorybox a').append('<div id="advisoryText">' + advisoryText + '</div>');
       $('#advisoryText').css('background-color','#FFF');
       $('#advisoryText').css('height','130px');
       $('#advisoryText').css('width','450px');
       $('#advisoryText').css('position','relative');
       $('#advisoryText').css('top','260px');
       $('#advisoryText').css('left','150px');
       $('#advisoryText').css('font-size','1.5rem');
       
       //Add a fake book now button
       $('#advisoryText').append('<span id="fakeButton">Book Now</span>');
       $('#fakeButton').css('background-color','blue');
       $('#fakeButton').css('color','#fff');
       $('#fakeButton').css('border-radius','5px');
       $('#fakeButton').css('position','relative');
       $('#fakeButton').css('top','10px');
       $('#fakeButton').css('left','104px');
  
       
       
       

    });
      
}())

