

window.onload = () => {
	console.log("window.onload")
    let btn = document.getElementById("submit");
    btn.addEventListener("click", submit);
}


function submit(){
	console.log("clicked")
    let user = document.getElementById("username").value;
    let pass = document.getElementById("password").value;
    console.log(user + " " + pass)

    var userObj = {username: user, password: pass};
    console.log(JSON.stringify(userObj));

    const http = new XMLHttpRequest();
    
    http.onreadystatechange = () =>{
        console.log(http.readyState);
        
        if((http.readyState == 4) && (http.status == 200)){
        	let user = JSON.parse(http.responseText);
            console.log(user);
            
        	if(user.position == 1){
            	window.location = 'http://localhost:8080/Project1/employee.html'
            } else if(user.position == 0){
            	window.location = 'http://localhost:8080/Project1/manager.html'
            }
        }   
    }
    
    http.open("get", 'http://localhost:8080/Project1/login?method=login&username='+user+'&password=' + pass, true);
    http.send();

    

    //window.location = 'http://localhost:8080/Project1/employee.html'
    
}

