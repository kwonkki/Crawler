
//Developer:Chris Lane
//Fix for pattern_id=27545
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27545
//Provide a valid label for form fields (ADD NEW GUEST dialog form fields are not labelled)
(function(){
    
    'use strict'

    $(document).ready(function () {
        
        if(window.location.pathname.match('CustomerPassengerList.aspx')) {
            //Remove inplicit labels
            $("#popupDialogGuest fieldset div>div:contains('BIRTHDATE')").remove();
            $("#PassengerListView_DropDownListDOBYear_1").parent().next().remove();
            
            //Add a visual label to each field since the placeholder disappear when an input when the user begins
            //entering text into an input field
            var id = 'PassengerListView_DropDownListTitle_1';
            $('#' + id).before('<label for="' + id + '">Title</label>');
            id = 'PassengerListView_TextBoxFirstName_1';
            $('#' + id).before('<label for="' + id + '">First Name</label>');
            id = 'PassengerListView_TextBoxLastName_1';
            $('#' + id).before('<label for="' + id + '">Last Name</label>');
            id = 'PassengerListView_DropDownListDOBMonth_1';
            $('#' + id).before('<label for="' + id + '">Month</label>');
            $('#' + id).attr('title','month of birth');
            id = 'PassengerListView_DropDownListDOBDay_1';
            $('#' + id).before('<label for="' + id + '">Day</label>');
            $('#' + id).attr('title','day of birth');
            id = 'PassengerListView_DropDownListDOBYear_1';
            $('#' + id).before('<label for="' + id + '">Year</label>');
            $('#' + id).attr('title','year of birth');
            id = 'PassengerListView_DropDownListNationality_1';
            $('#' + id).before('<label for="' + id + '">Nationality</label>');
            
            //Simple styling
            $("#popupDialogGuest label").css('margin','0 5px 0 5px');
            $("#popupDialogGuest label").css('font-weight','bold');

        }
    });
}())

