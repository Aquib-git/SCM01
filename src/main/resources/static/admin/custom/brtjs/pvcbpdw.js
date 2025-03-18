import { departmentDynaFilter, doctorDynaFilter } from './coreBirtUtils.js';
import { quarterFilter } from './coreBirtUtils.js';
import { yearFilter } from './coreBirtUtils.js';


//----------------------------- PATIENT VISIT Categorized By Procedure-Doctor Wise(Quarter) Report FORM SUBMIT LOGIC START----------------------------------
document.addEventListener('DOMContentLoaded', function (){
			
		// ----------------------------------- UTILITIES START -----------------------------------
		 	departmentDynaFilter('oid10', 'did10');
	 		doctorDynaFilter('did10', 'docid10');
	 		quarterFilter('qid10')
		 	yearFilter('yearid10');
		// ----------------------------------- UTILITIES  END -----------------------------------

		 var form = document.getElementById('pvcbpdw'); // Get the form by its ID

    	form.addEventListener('submit', function (e) {

        var oid = document.getElementById('oid10').value;
        var did = document.getElementById('did10').value;
        var docid = document.getElementById('docid10').value;
        var qid = document.getElementById('qid10').value;
        var yearid = document.getElementById('yearid10').value;
		var error = document.getElementById('pvcbpdw-error-message'); // Ensure you have this element in your HTML

		
        // ----------------------------------- QUERY STRING CONSTRUCTOR START -----------------------------------
       
        var queryString='?brtOid=' + encodeURIComponent(oid) +'&brtDid=' +encodeURIComponent(did) + '&brtDocid=' +encodeURIComponent(docid) + '&brtQrterid=' +encodeURIComponent(qid) +'&brtYearid=' +encodeURIComponent(yearid);
		
		// ----------------------------------- QUERY STRING CONSTRUCTOR END -----------------------------------
        
        // Only change the window location if form validation passes
        if (!e.defaultPrevented) {
            window.location.href = form.getAttribute('action') + queryString;
        }
    });

});


//----------------------------- PATIENT VISIT Categorized By Procedure-Doctor Wise(Quarter) Report FORM SUBMIT LOGIC END----------------------------------
