function ajaxGetRequest(url, expression, method = "get"){ //having method makes this more modular
    //step 1
    const xhr = new XMLHttpRequest(); //this object has to ability to send requests and get responses

    //step 2
    xhr.onreadystatechange = () => { //event handler for when the ready state changes
        if(xhr.readyState === 4 && xhr.status === 200){
            /*
                status = status code (100 info, 200 ok, 300 redirect, 400 client error, 500 server error)

                the ready state is like the state of the request, the request has a numerical representation
                ready state
                    0 = request has not been initialized
                    1 = request has been initialized (the connection has been established)
                    2 = request has been received
                    3 = server is processing the request
                    4 = request has finished processing and the response is ready
            */

            const jsonResponse = JSON.stringify(xhr.responseText); //Two main functions, parse and stringify
                                                                //Get from a server, parse
                                                                //Send to a server, stringify
            expression(jsonResponse);  
        }
    };

    //step 3
    xhr.open(method, url); //opens the connection

    //you can do different things between open and send, such as attaching headers

    //step 4
    xhr.send(); //sends the request
}

// async function asyncFetch(url, expression){ //async introduces asynchronous functions to javascript. by default, js is synchronous
//     const response = await fetch(url);      //await tells js to wait for something to come back, and resolve the promise, assigning response to a variable
//     const json = await response.json();
//     expression(json);
//     //this is not a function you want to use a return keyword on
//     //async and await do not make for return functions
// }

function renderTable(reimbursements){
    for (const reimbursement of reimbursements){
        const tr = document.createElement('tr');
        const typeTd = document.createElement('td');
        const amountTd = document.createElement('td');
        const authorTd = document.createElement('td');
        const submitTd = document.createElement('td');
        const descTd = document.createElement('td');
        const statTd = document.createElement('td');
        const resTd = document.createElement('td');
        const edTd = document.createElement('td');
        const edBt = document.createElement('input')
        typeTd.innerText = reimbursement.type.type;
        amountTd.innerText = reimbursement.amount;
        authorTd.innerText = reimbursement.author.username;
        submitTd.innerText = reimbursement.submitDate;
        descTd.innerText = reimbursement.description;
        statTd.innerText = reimbursement.status.status;
        resTd.innerText = reimbursement.resolveDate;

        edBt.value = reimbursement.reimbursementId;
        edBt.setAttribute(type,submit);

        if(reimbursement.status.status == "submitted"){
            edBt.disabled = false;
        } else{
            edBt.disabled = true;
        }

        edTd.append(edBt);

        tr.append(typeTd);
        tr.append(amountTd);
        tr.append(authorTd);
        tr.append(submitTd);
        tr.append(descTd);
        tr.append(statTd);
        tr.append(resTd);
        tr.append(edTd);
        document.getElementById('reimbTableBody').append(tr);
    }
}

// ajaxGetRequest(
//     "http://localhost:8081/Project1-1.0.0/html/table.json",renderTable,'get'
// );

// asyncFetch(
//     "http://18.216.107.51:8081/Project1-1.0.0/html/table.json",renderTable
// )

const x = fetch('http://18.216.107.51:8081/Project1-1.0.0/table.json', {
      //body: JSON.parse(),
      method: 'post',
     mode: "no-cors",
     headers:{
         origin: 'localhost'
     } 
  })
  .then((r)=>r.json())
  .then((reimbursements) => renderTable(reimbursements));

console.log("aaa"+reimbursements.data);