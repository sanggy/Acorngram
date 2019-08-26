//import $ from "jquery";

var nav:HTMLElement = document.querySelector('header nav');

var masonry = new Macy({
	container: '.timeline',
	columns : 2,
	margin: 20,
	trueOrder: true
}); 

nav.children[0].addEventListener('click', ()=>{
	const list:HTMLElement = nav.children[1];
	if(list.style.display === 'none' || list.style.display === ''){
		list.style.display = 'block';
	}else{
		list.style.display = 'none';
	}
});

function toggleWritePopup(){
	var block:HTMLElement = document.querySelector('.write-post');
	block.classList.toggle('is-visible');
}

function confirmAccess(url){
	var result:boolean = window.confirm('정말로 하시겠습니까?');
	if(result){
		location.href = url;
	}
}



function likeControl(num){
	
}

function deletePost(num){
	var result:boolean = window.confirm('정말로 삭제하시겠습니까?');
	if(result){
		$.ajax({
			url : "post/private/delete.jsp",
			type : "get",
			data : {postnum : num},
			dataType: "json",
		}).done(
			res => {
				console.log(res);
				if(res.result){
					window.alert('성공적으로 삭제되었습니다.');
					$('.post-'+num).fadeOut("ease",function(){$(this).remove();masonry.reInit();});
				}else{
					window.alert('오류가 발생했습니다.');
				}
			}
		);
	}
}

$('#write-post-img').on('change', function (e) {
    var reader = new FileReader();
    reader.onload = function (e) {
        $("#preview").attr('src', e.target.result);
    }
    reader.readAsDataURL(e.target.files[0]);
});

/*
$('#write-post button').click(()=>{
	const formdata = new FormData($('#write-post')[0]);
	$.ajax({
		url : "ajax/writePost.jsp",
		type : "post",
		data: formdata,
		cache: false,
		processData: false,
		dataType: "json",
		success: res=>{
			$('<span/>').text("처리결과 : "+res.result).appendTo('section.panel-alert');
			$('section.panel-alert').show(500, function(){
				$('section.panel-alert').hide();
			});
		}
	});
});
*/