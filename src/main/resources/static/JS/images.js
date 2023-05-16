function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#imagePreview').css('background-image', 'url('+e.target.result +')');
            $('#imagePreview').hide();
            $('#imagePreview').fadeIn(650);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
$("#imageUpload").change(function() {
    readURL(this);
});

 function updateFileCard(filename, filesize, downloadLink) {
 document.getElementById('filename').textContent = filename;
 document.getElementById('filesize').textContent = filesize;
 document.getElementById('downloadLink').href = downloadLink;
 document.getElementById('downloadLink').textContent = filename;
                                             }