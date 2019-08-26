var signUpMode = false;
var form = <HTMLFormElement>document.querySelector('#signForm');
var title = <HTMLElement>document.querySelector('#signForm legend');
var username = <HTMLElement>document.querySelector('.form-name');
var email = <HTMLElement>document.querySelector('.form-email');
var pwd = <HTMLInputElement>document.querySelector('input#pwd');
var pwdC = <HTMLInputElement>document.querySelector('input#pwd-c');

form.action = 'signIn.jsp';
username.style.display = 'none';
email.style.display = 'none';
pwdC.style.display = 'none';

document.querySelector('#signForm button[type="button"]').addEventListener('click', e=>{

	if(signUpMode){
		form.action = 'signIn.jsp';
		username.style.display = 'none';
		email.style.display = 'none';
		pwd.placeholder = '비밀번호';
		pwdC.style.display = 'none';
		e.target.innerText = '회원가입 하기';
		title.innerText = 'SIGN IN';
		signUpMode = false;
	}else{
		form.action = 'signUp.jsp';
		username.style.display = 'block';
		email.style.display = 'block';
		pwd.placeholder = '비밀번호 (8자리 이상)';
		pwdC.style.display = 'inline-block';
		e.target.innerText = '이미 회원이라면';
		title.innerText = 'SIGN UP';
		signUpMode = true;
	}
});
form.addEventListener('submit',e=>{

	if(signUpMode){
		if((<HTMLInputElement>document.querySelector('.form-id input')).value.length<4 ||
		(<HTMLInputElement>document.querySelector('.form-name input')).value.length<4 ){
			alert('아이디 혹은 이름은 4자 이상입니다.');
			e.preventDefault(); e.stopPropagation();
		}else if(!(<HTMLInputElement>document.querySelector('input[type="email"]'))
			.value.match(/[^\s]@[^\s]/g)){
			alert('이메일 형식이 맞지 않습니다.');
			e.preventDefault(); e.stopPropagation();
		}else if(pwd.value.length < 8){
			alert('비밀번호는 8자리 이상입니다.');
			e.preventDefault(); e.stopPropagation();
		}else if(pwd.value !== pwdC.value){
			alert('비밀번호가 서로 다릅니다.');
			e.preventDefault(); e.stopPropagation();
		}

	}else{
		form.submit();
	}

});