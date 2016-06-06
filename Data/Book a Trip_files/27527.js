//Developer:Chris Lane
//Fix for pattern_id=27527
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27527
//Ensure that simulated dialogs are rendered inline with the controls that activate the...(The ADD NEW CARD button (#AddCardLink) actually comes before the dialog (#ModuleContents) in the DOM)
//Dependencies: This fix is dependent on the fix for pattern 27528 so it must run after that fix in order to work
(function(){
    'use strict';
    //The fix is to move focus to the CARD TYPE field after the ADD NEW CARD dialog gains focus.
    //Moving focus to to the CARD TYPE field in in ADD NEW CARD dialog satisfies this best practice.
    //See the paragraph below on ‘https://amp.ssbbartgroup.com/public/standards/view_best_practice.php?report_id=263060&violation_id=890’.
    //‘When focus is moved to a control in the dialog when opened (especially modal dialogs that do not allow user interaction outside the dialog) this best practice is not required to determine compliance.
    $('#addNewCardDialog').focus(function(){
        $('#ControlGroupCreditCardDetailsView_CreditCardInputCreditCardDetailsView_DropDownListPaymentMethodCode-button').focus();
    });  
}())


