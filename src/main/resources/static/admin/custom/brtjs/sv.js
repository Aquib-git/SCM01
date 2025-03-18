import { dateValidator } from './coreBirtUtils.js';
import { kwtNkwtFilter } from './coreBirtUtils.js';

//----------------------------- SPECIALITY VISIT Report FORM SUBMIT LOGIC START----------------------------------
			
document.addEventListener('DOMContentLoaded', function (){
			
		// ----------------------------------- KWTI - NKWTI FILTER START -----------------------------------
		 	kwtNkwtFilter('kwtid11');
		// ----------------------------------- KWTI - NKWTI FILTER START -----------------------------------
		
			
		 var form = document.getElementById('sv'); // Get the form by its ID

    	form.addEventListener('submit', function (e) {

       
        var sdate = document.getElementById('sdate11').value;
        var edate = document.getElementById('edate11').value;
        var oid = document.getElementById('oid11').value;
        var kwtid= document.getElementById('kwtid11').value;
        var error = document.getElementById('sv-error-message'); // Ensure you have this element in your HTML
        
 
 		// ----------------------------------- BIRT DATE FILTER VALIDATOR CALLED HERE START -----------------------------------
        if (!dateValidator(sdate, edate, error))
        {
            e.preventDefault(); // Prevent form submission if validation fails
            return; // Exit the function
        }
 		// ----------------------------------- BIRT DATE FILTER VALIDATOR CALLED HERE END -----------------------------------
       

        // ----------------------------------- QUERY STRING CONSTRUCTOR START -----------------------------------
       
        var queryString='?brtSdate=' + encodeURIComponent(sdate) + '&brtEdate=' + encodeURIComponent(edate)+ '&brtOid=' +encodeURIComponent(oid)+ '&brtKwtNkwtid=' +encodeURIComponent(kwtid);
		
		// ----------------------------------- QUERY STRING CONSTRUCTOR END -----------------------------------
        
        // Only change the window location if form validation passes
        if (!e.defaultPrevented) {
            window.location.href = form.getAttribute('action') + queryString;
        }
    });

});
		
//----------------------------- SPECIALITY VISIT Report FORM SUBMIT LOGIC END----------------------------------
