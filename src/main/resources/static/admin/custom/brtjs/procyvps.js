import { yearFilter } from './coreBirtUtils.js';

//----------------------------- PATIENT YEARLY VISIT Categorized By Procedure Report FORM SUBMIT LOGIC START----------------------------------
			
document.addEventListener('DOMContentLoaded', function (){
			
		// ----------------------------------- MONTH and YEAR FILTER START -----------------------------------
		 	yearFilter('yearid9');
		// ----------------------------------- MONTH and YEAR FILTER END -----------------------------------

		 var form = document.getElementById('procyvps'); // Get the form by its ID

    	form.addEventListener('submit', function (e) {

        var oid = document.getElementById('oid9').value;
        var yearid = document.getElementById('yearid9').value;
        
        
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
		
//----------------------------- PATIENT YEARLY VISIT Categorized By Procedure Report FORM SUBMIT LOGIC START----------------------------------
