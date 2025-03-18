import { yearFilter } from './coreBirtUtils.js';
import { officeFilterWithoutAllCondition } from './coreBirtUtils.js';
//----------------------------- PATIENT YEARLY VISIT per SPECIALITY Report FORM SUBMIT LOGIC START----------------------------------
			
document.addEventListener('DOMContentLoaded', function (){
			
		// ----------------------------------- BIRT FILTER START -----------------------------------
		 	yearFilter('yearid7');
		 	officeFilterWithoutAllCondition('oid7');
		// ----------------------------------- BIRT FILTER END -----------------------------------

		 var form = document.getElementById('pyvps'); // Get the form by its ID

    	form.addEventListener('submit', function (e) {

        var oid = document.getElementById('oid7').value;
        var yearid = document.getElementById('yearid7').value;
        
        
        //var error = document.getElementById('pmvps-error-message'); // Ensure you have this element in your HTML

        // ----------------------------------- QUERY STRING CONSTRUCTOR START -----------------------------------
       
        var queryString='?brtOid=' + encodeURIComponent(oid) + '&brtYearid=' +encodeURIComponent(yearid);
		
		// ----------------------------------- QUERY STRING CONSTRUCTOR END -----------------------------------
        
        // Only change the window location if form validation passes
        if (!e.defaultPrevented) {
            window.location.href = form.getAttribute('action') + queryString;
        }
    });

});
		
//----------------------------- PATIENT YEARLY VISIT per SPECIALITY Report FORM SUBMIT LOGIC END----------------------------------
