
	let mouseenterColor = '#BDEDFF';
	let mouseleaveColor = 'white';
	let mouseClickColor = '#87CEFA';
	let bgColorProperty = 'background-color';
	
	
	function selectDiaryTrSetting(diaryTrId, selectedDiaryTrIdInputId, deleteBtnId, clearBtnId){
		
		$('#' + diaryTrId).click(function(){
			
			selectDiaryTrHandler(diaryTrId, selectedDiaryTrIdInputId);
			$('#' + deleteBtnId).prop('disabled', false);
			$('#' + clearBtnId).prop('disabled', false);
		});
	}
	function selectDiaryTrHandler(diaryTrId, selectedDiaryTrIdInputId){
		
		clickColorHandler(diaryTrId);
		
		var selectedDiaryTrIdInput = $('#' + selectedDiaryTrIdInputId);
		var originalSelectedDiaryTrId = selectedDiaryTrIdInput.val();
		
		if('' != originalSelectedDiaryTrId){
			
			$('#' + originalSelectedDiaryTrId).css(bgColorProperty, 'white');
			mouseenterChangeColor(originalSelectedDiaryTrId);
			mouseleaveChangeColor(originalSelectedDiaryTrId);
		}
		
		selectedDiaryTrIdInput.val(diaryTrId);
	}
	
	
	
	function mouseenterChangeColor(diaryTrId){
		
		$('#' + diaryTrId).mouseenter(function(){
			
			mouseenterColorHandler(diaryTrId);
		});
	}
	function mouseleaveChangeColor(diaryTrId){
		
		$('#' + diaryTrId).mouseleave(function(){
			
			mouseleaveColorHandler(diaryTrId);
		});
	}
	
	
	function clickColorHandler(diaryTrId){
		
		var diaryTr = $('#' + diaryTrId);
		diaryTr.css(bgColorProperty, mouseClickColor);
		diaryTr.off('mouseenter');
		diaryTr.off('mouseleave');
	}
	function mouseenterColorHandler(diaryTrId){
		
		$('#' + diaryTrId).css(bgColorProperty, mouseenterColor);
	}
	function mouseleaveColorHandler(diaryTrId){
		
		$('#' + diaryTrId).css(bgColorProperty, mouseleaveColor);
	}