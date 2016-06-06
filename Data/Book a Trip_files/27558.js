//Developer:Chris Lane
//Fix for pattern_id=27558
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27558
//Ensure that simulated dialogs can be closed via the keyboard(The ADD NEW GUEST dialog cannot be closed with the keyboard)

(function(){
    
    'use strict'

    $(document).ready(function () {
        
        if (window.location.pathname.match('CustomerPassengerList.aspx')) {
           $('#PassengerListView_btnSave').next().attr('href',"#");//Add the cancel button to the Tab order
           $('#PassengerListView_btnSave').next().attr('role',"button");//Define the link as a button            
					 //If the ESCAPE key is pressed hide the Add New Guest dialog
					 $(window).each(function(){
						$(this).on('keydown', function(e) {
								 e.stopPropagation();
								 if( e.which === 27 ) {
							 $('#popupDialogGuest').hide();
								 }
						 });
					});
        }
    });
    
}())


