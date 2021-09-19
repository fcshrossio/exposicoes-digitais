import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appDraganddroplist]'
})
export class DraganddroplistDirective {

  dropClassName = ""


  @HostListener('drag', ['$event']) public onDrag(evt: any) {
    //evt.preventDefault();
    // //evt.stopPropagation();
    

    console.log('Drag');
  }

  @HostListener('dragstart', ['$event']) public onDragStart(evt: any) {
    evt.dataTransfer.setData("text/plain", evt.target.id);
    console.log(evt.target.id)
    console.log('Drag start');
  }


  @HostListener('drop', ['$event']) public onDrop(evt: any) {
    evt.preventDefault();
    //evt.stopPropagation();
    if ( true ) {
      evt.target.style.background = "";
      console.log('Drop');
      var data = evt.dataTransfer.getData("text");
      console.log(data)
      console.log(evt.target.id)
      if(evt.target.id != data){

         var target = document.getElementById(evt.target.id)
         var original = document.getElementById(data)
         if(target&&original){
        //   var targetclone = target.cloneNode(true)
        //   var originalclone = original.cloneNode(true)

        //   target.parentNode?.replaceChild(originalclone, target)
        //   original.parentNode?.replaceChild(targetclone, original)
          //evt.target.replaceWith(document.getElementById(data))
            target.after(original)
        }
      }
       
      //evt.target.appendChild(document.getElementById(data));
      evt.dataTransfer.clearData();
    }

  }

  @HostListener('dragover', ['$event']) public onDragOver(evt: any) {
    evt.preventDefault();
  

  }

  @HostListener('dragenter', ['$event']) public onDragEnter(evt: any) {
    //console.log('Drag Enter');
    if ( evt.target.className == "col-fluid section draggable" ) {
      evt.target.style.background = "purple";
    }
  }

  @HostListener('dragleave', ['$event']) public onDragLeave(evt: any) {

    console.log('Drag Leave');
    if ( evt.target.className == "col-fluid section draggable" ) {
      evt.target.style.background = "";
    }
  }
  
}
