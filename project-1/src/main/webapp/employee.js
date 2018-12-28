// window.onload = () => {
//     let viewBtn = document.getElementById("viewReq")
//     viewBtn.addEventListener("click", pendingReq)
//     let newBtn = document.getElementById("newReq")
//     newBtn.addEventListener("click", newRequest)
//     let clrBtn = document.getElementById("newReq")
//     clrBtn.addEventListener("click", clear)
// }


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

    http.open("get", 'http://localhost:8080/Project1/employee?method=pendingReimb&id=1', true);
    http.send();

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

    http.open("get", 'http://localhost:8080/Project1/employee?method=resolvedReimb', true);
    http.send();

}

function newRequest(){
    clearChildren();
    let theParent = document.getElementById("parent");
    theParent.innerHTML = "Enter Information"
    
    let theDiv = document.createElement("div")

    let amount = document.createElement("label")
    amount.innerHTML = "$value"

    let amountBox = document.createElement("input")
    amountBox.setAttribute("type", "text");
    amountBox.setAttribute("id", "amountBox");
    amount.appendChild(amountBox);
    theDiv.appendChild(amount);
    theParent.appendChild(theDiv);

    let newDiv = document.createElement("div")

    let reason = document.createElement("label")
    reason.innerHTML = "reason"

    let reasonBox = document.createElement("input")
    reasonBox.setAttribute("type", "text");
    reasonBox.setAttribute("id", "reasonBox");
    reason.appendChild(reasonBox);
    newDiv.appendChild(reason);
    theParent.appendChild(newDiv);

    let subDiv = document.createElement("div");

    let subBtn = document.createElement("button");
    subBtn.setAttribute("onclick", "subRequest()");
    subBtn.innerHTML = "Submit";

    subDiv.appendChild(subBtn);
    theParent.appendChild(subDiv);


}

function subRequest(){
    console.log("submitted")
    let amount = document.getElementById("amountBox").value;
    let reason = document.getElementById("reasonBox").value;

    const http = new XMLHttpRequest();

    http.open("get", 'http://localhost:8080/Project1/employee?method=newReimb&amount=' + amount + '&reason=' + reason, true);
    http.send();
    clearChildren();
}

function changeEmail(){
    clearChildren();
    console.log("email updated");
    let theParent = document.getElementById("parent");

    let newDiv = document.createElement("div")

    let newEmail = document.createElement("label")
    newEmail.innerHTML = "newEmail"

    let emailBox = document.createElement("input")
    emailBox.setAttribute("type", "text");
    emailBox.setAttribute("id", "emailBox");
    newEmail.appendChild(emailBox);
    newDiv.appendChild(newEmail);
    theParent.appendChild(newDiv);

    let subDiv = document.createElement("div");

    let subBtn = document.createElement("button");
    subBtn.setAttribute("onclick", "updateEmail()");
    subBtn.innerHTML = "Submit";

    subDiv.appendChild(subBtn);
    theParent.appendChild(subDiv);

}

function updateEmail(){
    const http = new XMLHttpRequest();
    console.log("email updated");
    let newEmail = document.getElementById("emailBox").value;

    http.open("get", 'http://localhost:8080/Project1/employee?method=updateEmail&email=' + newEmail, true);
    http.send();
    
    clearChildren();
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

function viewInfo(){
	clearChildren();
	const http = new XMLHttpRequest();
	
	http.onreadystatechange = () =>{
		if((http.readyState == 4) && (http.status == 200)){
			let user = JSON.parse(http.responseText);
			
			console.log(user);
			
			let theParent = document.getElementById("parent");
			
			let div1 = document.createElement("div");
			let label1 = document.createElement("label");
			label1.innerHTML = "ID: " + user.id;
			div1.appendChild(label1);
			
			let div2 = document.createElement("div");
			let label2 = document.createElement("label");
			label2.innerHTML = "Username: " + user.fName;
			div2.appendChild(label2);
			
			let div3 = document.createElement("div");
			let label3 = document.createElement("label");
			label3.innerHTML = "Name: " + user.username;
			div3.appendChild(label3);
			
			let div4 = document.createElement("div");
			let label4 = document.createElement("label");
			label4.innerHTML = "Email: " + user.email;
			div4.appendChild(label4);
			
			let div5 = document.createElement("div");
			let label5 = document.createElement("label");
			label5.innerHTML = "Password: " + user.lName;
			div5.appendChild(label5);
			
		    theParent.appendChild(div1);
		    theParent.appendChild(div2);
		    theParent.appendChild(div3);
		    theParent.appendChild(div4);
		    theParent.appendChild(div5);
		    
			
		}
	}
	
	http.open("get", 'http://localhost:8080/Project1/employee?method=viewInfo', true);
    http.send();
}


