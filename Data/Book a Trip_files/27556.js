


//Developer:Chris Lane
//Fix for pattern_id=27556
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27556
//Ensure that keyboard focus remains within modal simulated dialogs... (You can TAB out of the ADD NEW GUEST dialog and put focus on the button (#PassengerListView_btnAdd) that opens the dialog)
(function(){
    
    'use strict'

    $(document).ready(function () {
        
        if(window.location.pathname.match('CustomerPassengerList.aspx')) {
           
             $('#PassengerListView_btnSave').next().attr('href',"#");//Add the cancel button to the Tab order
            
             //If a SHIFT + TAB occurs on the Add New Guest dialog, focus the cancel button
             $('#PassengerListView_DropDownListTitle_1').on('keydown', function(e) {
                e.stopPropagation();
                if(e.shiftKey  &&  e.keyCode === 9) {
                    $(this).blur( function(){
                       $('#PassengerListView_btnSave').next().focus();//put focus on the Cancel button
                       $(this).unbind('blur');//So you can TAB forward from this field later
                    });
                }
             });
            
         
             $('#PassengerListView_btnSave').next().on('keydown', function(e) {
                e.stopPropagation();
                //If the TAB key is pressed on the Cancel button, move focus to the Title field
                if(e.keyCode === 9) {
                    $(this).unbind('blur');
                    $(this).blur( function(){
                       $('#PassengerListView_DropDownListTitle_1').focus();
                    });
                }
                
                //If a SHIFT + TAB is pressed on the Cancel Button move focus to the Save Button
                if(e.shiftKey  &&  e.keyCode === 9) {
                   $(this).unbind('blur');//So you can SHIFT TAB from the Cancel Button
                   $(this).blur( function(){
                      $('#PassengerListView_btnSave').focus();
      
                   });
                }
             });   
        }
    });
}())


