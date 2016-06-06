//Developer:Chris Lane
//Fix for pattern_id=27557
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27557
//Dependencies: This fix is dependent on the fix for pattern 27552
(function(){
    'use strict'
    $(document).ready(function () {
        if(window.location.pathname.match('CustomerPassengerList.aspx')) {
            //The fix was to move focus to the TITLE fields once the dialog gained focu
            if(document.activeElement.id === 'popupDialogGuest'){
                $('#PassengerListView_DropDownListTitle_1').focus();
            }
        }
    });
}())

