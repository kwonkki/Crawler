//Developer:Chris Lane
//Fix for pattern_id=27554
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27554
//Ensure links that spawn simulated dialogs indicate the fact (The ADD NEW GUEST link does not indicate that it will open a dialog)
(function(){
    
    'use strict'

    $(document).ready(function () {
        
        if(window.location.pathname.match('CustomerPassengerList.aspx')) {
      	    $('#PassengerListView_btnAdd').attr('role','button');
      	    $('#PassengerListView_btnAdd').attr('title','this button opens the add new guest dialog');
            $('#PassengerListView_btnAdd').attr('aria-label','this button opens the add new guest dialog');
        }
    });
}())

