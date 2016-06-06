//Developer:Chris Lane
//Fix for pattern_id=27088
//https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27088
(function(){
    'use strict';
    $(document).ready(function () {
        if(window.location.pathname.match('Guest.aspx')) {
            //The Iframe is not user facing
            //If the frame is not user facing, set the tabindex to -1 and still give the frame a @title such "empty". It may also be useful to set the width and height to 0 to make sure the frame is not detected by screen readers and also set aria-hidden to true.
            $('#hotelPersonalizationsFrame').attr('title','empty');
            $('#hotelPersonalizationsFrame').attr('tabindex', -1);
            $('#hotelPersonalizationsFrame').attr('aria-hidden','true');
            //$('#hotelPersonalizationsFrame').css('height','0');
            //$('#hotelPersonalizationsFrame').css('width','0');
        }
    });
}())

