/*========================================================================================
  
$rcsfile: BookingRetrieveInput.js $

$Revision: 1.1 $ $Date: 2006/09/07 09:35:06 $

Summary:	JavaScript file for the BookingRetrieveInput control. Content moved from the C# code.

------------------------------------------------------------------------------------------
This file is part of the Navitaire NewSkies application.
Copyright (C) Navitaire.  All rights reserved.
========================================================================================*/



function BookingRetrieveValues_Validate()
{
    var ds = document['SkySales'];
	var sectionPopulateCounter = 0;    
    var missing = false;
    var empty = true;	
	
	//get the sections
	var sectionList = applicationSections.split(",")    
	
	for(var i=0; i < sectionList.length; i++)
	{
	    var controlList = sectionList[i].split("|");
	    var sectionIndex = i+1;
	    var controlPopulatedCount = 0;
	    
	    for(var j=0; j < controlList.length; j++)
	    {   
	        var controlID = applicationBookingRetrieveJavaScriptHtmlId + '_' + controlList[j] + sectionIndex;
	        var controlIsPopulated = false;


	        if (controlList[j] == "ORIGINCITY") {
                   if ( ds[controlID].value !="")
                    controlIsPopulated = ds[controlID].value != "Origin";
                }
                else 
                {
                    if (controlList[j] == "DESTINATIONCITY") {
                        if (ds[controlID].value != "")
                        controlIsPopulated = ds[controlID].value != "Destination";
                    }
                    else 
                    {
                        controlIsPopulated = (ds[controlID].value != $(ds[controlID]).attr('requiredEmpty') && ds[controlID].value != "");
                    }
                }
            
            if (controlIsPopulated)
            {
                controlPopulatedCount++;
                empty = false;
            }
	    }//end control loop

        //check if all items are answered
        missing = missing ||((controlPopulatedCount < controlList.length) && (controlPopulatedCount > 0));
	    
	    //monitor number of sections answered
        if (controlPopulatedCount > 0)
        {
            sectionPopulateCounter++;
        }       
	}//end section loop

	if (sectionPopulateCounter > 1)
	{
	    alert(localizedenteredInfoInMoreThanOneSection);
	    return false;
	}

    if (missing || empty)	
    {
        alert(localizedMissingInformation);
        return false;
    }        

	
	return true;
}