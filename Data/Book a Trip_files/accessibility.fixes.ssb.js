/**
 * @author Heidi Jungel
 */
(function () {
    'use strict'
    $(document).ready(function () {

        /*
		 * Some Slight adjustments made to https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27107
		 * since not all are menu items. 
		 * Also fix for https://amp.ssbbartgroup.com/public/reporting/view_pattern.php?pattern_id=27170
		 */

        //TODO: START Menu Navigation and labels
        //set heading id
        for (var i = 0; i < $('.menuContainer li.blue').length; i++) {
            $('.menuContainer li.blue').eq([i]).attr('id', 'head' + [i]);
        }

        //adding labels....
        $('#utilmenu', this).each(function () {
            $(this).attr('role', 'navigation').attr('aria-label', 'Main');

            $('ul.menuContainer', this).each(function () {
                $('.menuOptions', this).each(function () {

                    $('> a', this).each(function () {
                        if ($(this).next('div').length > 0) {
                            $(this).attr('role', 'button');
                            $(this).attr('aria-haspopup', 'true');

                            $(this).click(function () {
                                showMenuOptions(this);
                            }).mouseover(function () {
                                showMenuOptions(this);
                            });
                        }
                    });

                    //menu ul
                    $('ul', this).each(function () {
                        $(this).attr('role', 'menu');
                        $('li', this).each(function () {
                            $(this).attr('role', 'presentation');
                            $('a', this).each(function () {
                                var headingLabel = $(this).parent().siblings('.blue').attr('id');
                                $(this).attr('aria-describedby', headingLabel);
                                $(this).attr('role', 'menuitem');
                                $(this).keydown(function (e) {
                                    menuNav(e, this);
                                    if (e.which == 27) {
                                        $(this).parent().closest('.mm-item-content').prev('a').focus();
                                        $(this).parent().closest('.mm-item-content').hide();
                                        e.preventDefault();
                                    }
                                });
                            });
                        });
                    });

                });
            });

        });

        function showMenuOptions(that) {
            if ($(that).next().length > 0) {
                $('.mm-item-content').hide();
                $(that).next().show();
                $(that).next().find('.mmbTooltip').show();
                $(that).next('div').find('li:not(.blue)').children('a:first').focus();
            }
        }


        function menuNav(e, that) {
            switch (e.which) {
                case 38:
                    if (!$(that).parent().prev().children('a').length > 0) {
                        $(that).parent().parent().prev().find('li.bulletedList:last').children('a').focus();
                    } else {
                        $(that).parent().prev().children('a').focus();
                    }
                    break;
                case 40:
                    if (!$(that).parent().next().length > 0) {
                        $(that).parent().parent().next().find('li.bulletedList:first').children('a').focus();
                    } else {
                        $(that).parent().next().children('a').focus();
                    }
                    break;
            }
        }
        //TODO: END Menu Navigation
    });
})();



