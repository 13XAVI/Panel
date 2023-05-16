let usersArea = document.getElementById('users-area');

function showUsersInfo() {

  fetch('https://jsonplaceholder.typicode.com/users')
        .then(response => response.json())
        .then(addUsersCards)
}

showUsersInfo();

function addUsersCards(userData) {
    for (let i = 0; i < userData.length; i++) {
        usersArea.append(createUserCardLayout(userData[i]));
      // console.log(userData[i].id);

  }

function createUserCardLayout({id, name, username, company: {name:companyName}, phone, email, address: {city, street}, website}) {
    let userCard = document.createElement('div');
    userCard.classList.add('user-card');
    userCard.id = `${id}`;
    let userCardTemplate = `
<div class="user-card__photo"></div>
<div class="user-card__name">${name}</div>
<div class="user-card__nikname">@${username}</div>
<div class="user-card__company">Company: "${companyName}"</div>
<div class="user-card__phone">Phone: ${phone}</div>
<div class="user-card__email">E-mail: <a href="#">${email}</a></div>
<div class="user-card__address">Address: ${city}, ${street}</div>
<div class="user-card__website"><a href="#">www.${website}</a></div>
`;
  userCard.innerHTML = userCardTemplate;
  return userCard;
}

 usersArea.addEventListener('click', function (event){
        if (!event.target.classList.contains('user-card')){
           return;
 }
       getUserById(event.target.id);
   // console.log('contains');
      })
}

function getUserById(numId){
  fetch(`https://jsonplaceholder.typicode.com/users?id=${+numId}`)
        .then(response => response.json())
        .then(userPageTemplate)
  // .then(console.log(numId))
}

function userPageTemplate (userInfo){
  usersArea.innerHTML = userInfo[0].name;
  console.log('yes');

}