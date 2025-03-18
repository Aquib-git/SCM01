// ------------------------------- COMMON DATE VALIDATOR FUNCTION START ------------------------------- 
export function dateValidator(sdate, edate, errorElement) 
{
	var startDate = new Date(sdate);
    var endDate = new Date(edate);
    var currentDate = new Date();

    // Start Date must not be empty
    if (!sdate) 
    {
        errorElement.textContent = 'From date must not be empty.';
        return false; // Indicate validation failure
    }
    // End date must not be empty
    if (!edate) 
    {
        errorElement.textContent = 'To date must not be empty.';
        return false; // Indicate validation failure
    }
    // Start date should be less than end date
    if (startDate > endDate) 
    {
        errorElement.textContent = 'From date must be less than To date.';
        return false; // Indicate validation failure
    }
    // End date does not exceed current date
    if (endDate > currentDate) 
    {
        errorElement.textContent = 'To date should not exceed the current date.';
        return false; // Indicate validation failure
    }

    // Clear error message and indicate success
    errorElement.textContent = '';
    return true;
}
// ------------------------------- COMMON DATE VALIDATOR FUNCTION END ------------------------------- 

// ------------------------------- COMMON CLINIC VALIDATOR FUNCTION START ------------------------------- 

export function clinicValidator(oid, errorElement) 
{
	 if (oid === '0') {
        errorElement.textContent = 'Please select Clinic';
        return false; // Indicate validation failure
    }

    // Clear error message and indicate success
    errorElement.textContent = '';
    return true;
}
// ------------------------------- COMMON CLINIC VALIDATOR FUNCTION END ------------------------------- 

// ------------------------------- DYNAMIC DEPARTMENT FILTER OPTIONS  based on OFFICE ID START ------------------------------- 

export function departmentDynaFilter (officeId, departmentDropdownId) {
    document.getElementById(officeId).addEventListener('change', function() {
        var clinicId = this.value;
        fetch('/departmentList/' + clinicId)
            .then(response => response.json())
            .then(data => {
                var departmentDropdown = document.getElementById(departmentDropdownId);
                departmentDropdown.innerHTML = '<option th:value="0">-- Select Department / ALL --</option>';
                data.forEach(function(department) {
                    var option = new Option(department.departmentName, department.departmentId);
                    departmentDropdown.add(option);
                });
            })
            .catch(error => console.error('Error:', error));
    });
}
// ------------------------------- DYNAMIC DEPARTMENT FILTER OPTIONS  based on OFFICE ID END ------------------------------- 


// ------------------------------- DYNAMIC DOCTOR FILTER OPTIONS  based on DEPARTMENT ID START ------------------------------- 

export function doctorDynaFilter(departmentDropdownId, doctorDropdownId) {
    document.getElementById(departmentDropdownId).addEventListener('change', function() {
        var departmentId = this.value;
        fetch('/doctorList/' + departmentId)
            .then(response => response.json())
            .then(data => {
                var doctorDropdown = document.getElementById(doctorDropdownId);
                doctorDropdown.innerHTML = '<option th:value="0">-- Select Doctor / ALL --</option>';
                data.forEach(function(doctor) {
                    var option = new Option(doctor.doctorName, doctor.doctorId);
                    doctorDropdown.add(option);
                });
            })
            .catch(error => console.error('Error:', error));
    });
}
// ------------------------------- DYNAMIC DOCTOR FILTER OPTIONS  based on DEPARTMENT ID END ------------------------------- 


// ------------------------------- MONTH FILTER OPTIONS START ------------------------------- 

export function monthFilter(monthdropdownId) {
    const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    const monthDropdown = document.getElementById(monthdropdownId);

    monthNames.forEach((month, index) => {
        const option = new Option(month, index + 1); // Month id starts from 1 for January
        monthDropdown.add(option);
    });
}
// ------------------------------- MONTH FILTER OPTIONS END ------------------------------- 

// ------------------------------- YEAR FILTER OPTIONS START ------------------------------- 

export function yearFilter(yeardropdownId) {
    const yearDropdown = document.getElementById(yeardropdownId);
    const currentYear = new Date().getFullYear();
    const startYear = 2019;

    for (let year = startYear; year <= currentYear; year++) {
        const option = new Option(year, year);
        yearDropdown.add(option);
    }
}
// ------------------------------- YEAR FILTER OPTIONS END ------------------------------- 

// ------------------------------- QUARTER FILTER OPTIONS START ------------------------------- 
export function quarterFilter(quarterdropdownId){
	const quarterNames = ["First Quarter", "Second Quarter","Third Quarter", "Fourth Quarter" ];
    const quarterDropdown = document.getElementById(quarterdropdownId);

	 quarterNames.forEach((quarter, index) => {
        const option = new Option(quarter, index + 1); // Quarter id starts from 1 for First Quarter
        quarterDropdown.add(option);
    });

}
// ------------------------------- QUARTER FILTER OPTIONS END ------------------------------- 


// ------------------------------- KUWAITI- NON KUWAITI FILTER OPTIONS START ------------------------------- 
export function kwtNkwtFilter(kwtNkwtdropdownId){
	const kwtNkwtNames = ["All","Kuwaiti","Non-Kuwaiti"];
    const kwtNkwtDropdown = document.getElementById(kwtNkwtdropdownId);

	 kwtNkwtNames.forEach((kwtnkwt, index) => {
        const option = new Option(kwtnkwt, index + 1); // The id starts from 1 for All
        kwtNkwtDropdown.add(option);
    });

}

//	---- All=1,Kuwaiti=2, Non-Kuwaiti=3 -----
// ------------------------------- KUWAITI- NON KUWAITI FILTER OPTIONS END ------------------------------- 


// ------------------------------- DYNAMIC MEDICINE FILTER OPTIONS  based on OFFICE ID START ------------------------------- 

export function medicineDynaFilter(officeId, medicineDropdownId) {
    document.getElementById(officeId).addEventListener('change', function() {
        var clinicId = this.value;
        fetch('/medicineList/' + clinicId)
            .then(response => response.json())
            .then(data => {
                var medicineDropdown = $('#' + medicineDropdownId);
                medicineDropdown.empty(); // Clear existing options
                medicineDropdown.append('<option value="0"></option>');
                
                data.forEach(function(medicine) {
                    medicineDropdown.append(new Option(medicine.itemName, medicine.itemId));
                });

                // Re-initialize select2
                medicineDropdown.select2({
                    placeholder: "Seach and Select a medicine",
                    allowClear: true,
                    multiple: true, // Enable multiple selection
                    tags: true      // Allow creating new tags (medicines) if not found in the list
                });
            })
            .catch(error => console.error('Error:', error));
    });
}




// ------------------------------- DYNAMIC MEDICINE FILTER OPTIONS  based on OFFICE ID END ------------------------------- 


// ------------------------------- OFFICE FILTER OPTIONS START ------------------------------- 

export function officeFilterWithoutAllCondition(officedropdownId) {
   const officeNames = ["HeadQuarters-MultiSpeciality","Sheikh Salem Al Ali Camp","Summod Camp-MultiSpeciality","Tahreer Camp-Dental","Tahreer Camp-MultiSpeciality"];
    const officeDropdown = document.getElementById(officedropdownId);

    officeNames.forEach((office, index) => {
        const option = new Option(office, index + 1); // Month id starts from 1 for January
        officeDropdown.add(option);
    });
}

// ------------------------------- OFFICE FILTER OPTIONS END ------------------------------- 










