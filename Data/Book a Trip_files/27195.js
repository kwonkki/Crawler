//Developer:Chris Lane
//Fix for pattern_id=27195
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27195
//Best Practice: Ensure calendar components do not use color alone to convey selection/meaning
//Best Practice URL: https://amp.ssbbartgroup.com/public/standards/view_best_practice.php?report_id=263059&violation_id=1052
//Cebu's implementation of the AccDC calendars violates this best practice. They have add visual styling using the .selected class but there is no textual indication that a date is selected.
(function(){
    'use strict';
     //The following code needs to be added whenever the .selected class is applied.
     $('.calendar .selected').attr('aria-label','selected date');
}())

