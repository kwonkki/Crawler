(function() {
	'use strict'

var colorErrorRed="#df0000;";
var colorBlue="#2065a1;";
var colorGreyPlaceholder="#555;";
var style = document.createElement('style');
style.type = 'text/css';

var strStyle;
strStyle = "body.mybookings ul.inline.viewall li {color:#865e30;}";
strStyle += "#SkySales #pageTitle h1 {color:"+ colorBlue + "}";
strStyle += "body.ibe .red-text, body.ibe  #Exception, body.ibe .red, body.ibe .validationErrorLabel, body.ibe .mealDepartureNote{color:" + colorErrorRed + "}";
strStyle += ".validationErrorContainerReadAlongList > div {background-color:" + colorErrorRed + "}";
strStyle += "body.ibe form#SkySales div.row div input.validationError {border-color:" + colorErrorRed + "}";
strStyle += "body.ibe .dayHeaderTodayImage a, body.ibe .dynamicFeeLabel, body.ibe .customerFareRulesHeader, body.ibe #bookingSummaryTotalHead, body.ibe .amountLabel{color:"+ colorBlue +"}";
strStyle += "body.ibe form div.row div input:-ms-input-placeholder{color:"+ colorGreyPlaceholder +"}body.ibe form div.row div input::-moz-placeholder{color:"+ colorGreyPlaceholder +"}body.ibe form div.row div input:-moz-placeholder{color:"+ colorGreyPlaceholder +"}body.ibe form div.row div input::-webkit-input-placeholder{color:"+ colorGreyPlaceholder +"}";
strStyle += ".off-screen{clip: rect(0px, 0px, 0px, 0px);position: absolute;overflow:hidden;z-index:1;}"
strStyle += "#selectMainBody .tabsHeader .rightArrowButton a, .leftArrowButton a{text-decoration:none;};"
style.innerHTML= strStyle;

document.getElementsByTagName('head')[0].appendChild(style);
})();
