function pendingReq(){
    
}

function clearChildren(){
    console.log("clear");
    
    let theParent = document.getElementById("parent");
    while (theParent.firstChild) {
        theParent.removeChild(theParent.firstChild);
    }
}

function logout(){
	const http = new XMLHttpRequest();

    http.open("get", 'http://localhost:8080/Project1/login?method=logout', true);
    http.send();
    
    window.location = 'http://localhost:8080/Project1/login.html'
}

function pendingReq(){
    clearChildren();
    let theParent = document.getElementById("parent");

    let baseList = document.createElement("ul")
    baseList.innerHTML = "Pending Reimbursments"
    let table = document.createElement("table")
    table.setAttribute("class", "table")
    let tHead = document.createElement("thead")
    let row = document.createElement("tr")
    
    let header = document.createElement("th");
    header.innerHTML = "#";
    header.setAttribute("scope", "col");
    row.appendChild(header)
    let headRow1 = document.createElement("th")
    headRow1.innerHTML = "ID";
    headRow1.setAttribute("scope", "col");
    row.appendChild(headRow1);
    
    let headRow4 = document.createElement("th")
    headRow4.innerHTML = "employee ID";
    headRow4.setAttribute("scope", "col");
    row.appendChild(headRow4);
    
    let headRow2 = document.createElement("th")
    headRow2.innerHTML = "amount";
    headRow2.setAttribute("scope", "col");
    row.appendChild(headRow2);
    
    let headRow3 = document.createElement("th")
    headRow3.innerHTML = "reason";
    headRow3.setAttribute("scope", "col");
    row.appendChild(headRow3);
    
    tHead.appendChild(row);
    table.appendChild(tHead);
    theParent.appendChild(table);
    


    const http = new XMLHttpRequest();

    http.onreadystatechange = () =>{
        console.log(http.readyState);
        if((http.readyState == 4) && (http.status == 200)){
        	//console.log(http.responseText)
            var finished = JSON.parse(http.responseText);

            for(let i = 0; i < finished.length; i++){
            	let obj = finished[i];
            	//table = document.createElement("table")
            	let tBody = document.createElement("tbody")
                row = document.createElement("tr")
                let head = document.createElement("th")
                head.setAttribute("scope", "row");
            	head.innerHTML = i;
            	row.appendChild(head);
                headRow1 = document.createElement("td")
                text1 = document.createTextNode(obj.rid)
                headRow1.appendChild(text1)
                //headRow1.innerHTML = obj.rid
                row.appendChild(headRow1);
                
                headRow4 = document.createElement("td")
                text4 = document.createTextNode(obj.eid)
                headRow4.appendChild(text4)
                //headRow3.innerHTML = obj.reason
                row.appendChild(headRow4);
                
                headRow2 = document.createElement("td")
                text2 = document.createTextNode(obj.amount)
                headRow2.appendChild(text2)
                //headRow2.innerHTML = obj.amount
                row.appendChild(headRow2);
                headRow3 = document.createElement("td")
                text3 = document.createTextNode(obj.reason)
                headRow3.appendChild(text3)
                //headRow3.innerHTML = obj.reason
                row.appendChild(headRow3);
                
                aprBtn = document.createElement("button");
                
                aprBtn.innerHTML = "approve"
                aprBtn.onclick = function() {
                    approve(obj.rid);
                }
                
                row.appendChild(aprBtn);
                
                decBtn = document.createElement("button");
                
                decBtn.innerHTML = "decline"
                decBtn.onclick = function() {
                    decline(obj.rid);
                }
                
                row.appendChild(decBtn);
                
                tBody.appendChild(row);
                table.appendChild(tBody);
                theParent.appendChild(table);
            }

            
        }   
    }

    http.open("get", 'http://localhost:8080/Project1/manager?method=pendingReimb', true);
    http.send();

}

function approve(id){
	console.log("approved" + id)
	
	const http = new XMLHttpRequest();
	
	http.open("get", 'http://localhost:8080/Project1/manager?method=approve&id=' + id, true);
    http.send();
    pendingReq();
	
}
function decline(id){
	console.log("declined" + id)
	
	const http = new XMLHttpRequest();
	
	http.open("get", 'http://localhost:8080/Project1/manager?method=decline&id=' + id, true);
    http.send();
    
    pendingReq();
}

function resolvedReq(){
	clearChildren();
    let theParent = document.getElementById("parent");

    let baseList = document.createElement("ul")
    baseList.innerHTML = "Pending Reimbursments"
    let table = document.createElement("table")
    table.setAttribute("class", "table")
    let tHead = document.createElement("thead")
    let row = document.createElement("tr")
    
    let header = document.createElement("th");
    header.innerHTML = "#";
    header.setAttribute("scope", "col");
    row.appendChild(header)
    let headRow1 = document.createElement("th")
    headRow1.innerHTML = "ID";
    headRow1.setAttribute("scope", "col");
    row.appendChild(headRow1);
    
    let headRow4 = document.createElement("th")
    headRow4.innerHTML = "employee ID";
    headRow4.setAttribute("scope", "col");
    row.appendChild(headRow4);
    
    let headRow2 = document.createElement("th")
    headRow2.innerHTML = "amount";
    headRow2.setAttribute("scope", "col");
    row.appendChild(headRow2);
    
    let headRow3 = document.createElement("th")
    headRow3.innerHTML = "reason";
    headRow3.setAttribute("scope", "col");
    row.appendChild(headRow3);
    
    let headRow5 = document.createElement("th")
    headRow5.innerHTML = "ManagerID";
    headRow5.setAttribute("scope", "col");
    row.appendChild(headRow5);
    
    tHead.appendChild(row);
    table.appendChild(tHead);
    theParent.appendChild(table);
    


    const http = new XMLHttpRequest();

    http.onreadystatechange = () =>{
        console.log(http.readyState);
        if((http.readyState == 4) && (http.status == 200)){
        	//console.log(http.responseText)
            var finished = JSON.parse(http.responseText);

            for(let i = 0; i < finished.length; i++){
            	let obj = finished[i];
            	//table = document.createElement("table")
            	let tBody = document.createElement("tbody")
                row = document.createElement("tr")
                let head = document.createElement("th")
                head.setAttribute("scope", "row");
            	head.innerHTML = i;
            	row.appendChild(head);
                headRow1 = document.createElement("td")
                text1 = document.createTextNode(obj.rid)
                headRow1.appendChild(text1)
                //headRow1.innerHTML = obj.rid
                row.appendChild(headRow1);
                
                headRow4 = document.createElement("td")
                text4 = document.createTextNode(obj.eid)
                headRow4.appendChild(text4)
                row.appendChild(headRow4);
                
                headRow2 = document.createElement("td")
                text2 = document.createTextNode(obj.amount)
                headRow2.appendChild(text2)
                row.appendChild(headRow2);
                
                headRow3 = document.createElement("td")
                text3 = document.createTextNode(obj.reason)
                headRow3.appendChild(text3)
                row.appendChild(headRow3);
                
                headRow5 = document.createElement("td")
                text5 = document.createTextNode(obj.mid)
                headRow5.appendChild(text5)
                row.appendChild(headRow5);
                
                tBody.appendChild(row);
                table.appendChild(tBody);
                theParent.appendChild(table);
            }

            
        }   
    }

    http.open("get", 'http://localhost:8080/Project1/manager?method=resReimb', true);
    http.send();
}

function employees(){
    clearChildren();
    
    console.log("hello")
    
    let theParent = document.getElementById("parent");

    let baseList = document.createElement("ul")
    baseList.innerHTML = "Employee List"
    let table = document.createElement("table")
    table.setAttribute("class", "table")
    let tHead = document.createElement("thead")
    let row = document.createElement("tr")
    
    let header = document.createElement("th");
    header.innerHTML = "#";
    header.setAttribute("scope", "col");
    row.appendChild(header)
    let headRow1 = document.createElement("th")
    headRow1.innerHTML = "ID";
    headRow1.setAttribute("scope", "col");
    row.appendChild(headRow1);
    let headRow2 = document.createElement("th")
    headRow2.innerHTML = "Name";
    headRow2.setAttribute("scope", "col");
    row.appendChild(headRow2);
    let headRow3 = document.createElement("th")
    headRow3.innerHTML = "Username";
    headRow3.setAttribute("scope", "col");
    row.appendChild(headRow3);
    let headRow4 = document.createElement("th")
    headRow4.innerHTML = "Email";
    headRow4.setAttribute("scope", "col");
    row.appendChild(headRow4);
    tHead.appendChild(row);
    table.appendChild(tHead);
    theParent.appendChild(table);
    
    const http = new XMLHttpRequest();

    http.onreadystatechange = () =>{
        console.log(http.readyState);
        if((http.readyState == 4) && (http.status == 200)){
        	//console.log(http.responseText)
            var finished = JSON.parse(http.responseText);

            for(let i = 0; i < finished.length; i++){
            	let obj = finished[i];
            	//table = document.createElement("table")
            	let tBody = document.createElement("tbody")
                row = document.createElement("tr")
                let head = document.createElement("th")
                head.setAttribute("scope", "row");
            	head.innerHTML = i;
            	row.appendChild(head);
            	
                headRow1 = document.createElement("td")
                text1 = document.createTextNode(obj.id)
                headRow1.appendChild(text1)
                
                //headRow1.innerHTML = obj.rid
                row.appendChild(headRow1);
                headRow2 = document.createElement("td")
                text2 = document.createTextNode(obj.fName + " " + obj.lName)
                headRow2.appendChild(text2)
                
                //headRow2.innerHTML = obj.amount
                row.appendChild(headRow2);
                headRow3 = document.createElement("td")
                text3 = document.createTextNode(obj.username)
                headRow3.appendChild(text3)
                //headRow3.innerHTML = obj.reason
                row.appendChild(headRow3);
                
                headRow4 = document.createElement("td")
                text4 = document.createTextNode(obj.email)
                headRow4.appendChild(text4)
                //headRow3.innerHTML = obj.reason
                row.appendChild(headRow4);
                
                tBody.appendChild(row);
                table.appendChild(tBody);
                theParent.appendChild(table);
            }

            
        }   
    }

    http.open("get", 'http://localhost:8080/Project1/manager?method=viewEmp', true);
    http.send();
    
}

function pendingEmpReq(){
	
	clearChildren();
	
	let theParent = document.getElementById("parent");

	let theDiv = document.createElement("div")

    let label = document.createElement("label")
    label.innerHTML = "Employee ID"

    let empBox = document.createElement("input")
    empBox.setAttribute("type", "text");
    empBox.setAttribute("id", "empBox");
    
    console.log("make button")
    let empBtn = document.createElement("button");
    
    empBtn.innerHTML = "Submit"
    empBtn.onclick = function() {
        displayInfo(document.getElementById("empBox").value);
    }
    console.log("made button")
    label.appendChild(empBox);
    theDiv.appendChild(label);
    theDiv.appendChild(empBtn);
    theParent.appendChild(theDiv);
	
	
}


function displayInfo(id){
	clearChildren();
    console.log("displayInfo")
	let theParent = document.getElementById("parent");

    let baseList = document.createElement("ul")
    baseList.innerHTML = "Pending Reimbursments"
    let table = document.createElement("table")
    table.setAttribute("class", "table")
    let tHead = document.createElement("thead")
    let row = document.createElement("tr")
    
    let header = document.createElement("th");
    header.innerHTML = "#";
    header.setAttribute("scope", "col");
    row.appendChild(header)
    let headRow1 = document.createElement("th")
    headRow1.innerHTML = "ID";
    headRow1.setAttribute("scope", "col");
    row.appendChild(headRow1);
    let headRow2 = document.createElement("th")
    headRow2.innerHTML = "amount";
    headRow2.setAttribute("scope", "col");
    row.appendChild(headRow2);
    let headRow3 = document.createElement("th")
    headRow3.innerHTML = "reason";
    headRow3.setAttribute("scope", "col");
    row.appendChild(headRow3);
    tHead.appendChild(row);
    table.appendChild(tHead);
    theParent.appendChild(table);
    


    const http = new XMLHttpRequest();

    http.onreadystatechange = () =>{
        console.log(http.readyState);
        if((http.readyState == 4) && (http.status == 200)){
        	//console.log(http.responseText)
            var finished = JSON.parse(http.responseText);

            for(let i = 0; i < finished.length; i++){
            	let obj = finished[i];
            	//table = document.createElement("table")
            	let tBody = document.createElement("tbody")
                row = document.createElement("tr")
                let head = document.createElement("th")
                head.setAttribute("scope", "row");
            	head.innerHTML = i;
            	row.appendChild(head);
                headRow1 = document.createElement("td")
                text1 = document.createTextNode(obj.rid)
                headRow1.appendChild(text1)
                //headRow1.innerHTML = obj.rid
                row.appendChild(headRow1);
                headRow2 = document.createElement("td")
                text2 = document.createTextNode(obj.amount)
                headRow2.appendChild(text2)
                //headRow2.innerHTML = obj.amount
                row.appendChild(headRow2);
                headRow3 = document.createElement("td")
                text3 = document.createTextNode(obj.reason)
                headRow3.appendChild(text3)
                //headRow3.innerHTML = obj.reason
                row.appendChild(headRow3);
                tBody.appendChild(row);
                table.appendChild(tBody);
                theParent.appendChild(table);
            }

            
        }   
    }

    http.open("get", 'http://localhost:8080/Project1/manager?method=pendingEmpReimb&id=' + id, true);
    http.send();
	
}




