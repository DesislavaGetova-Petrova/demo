const videosList = document.getElementById('videosList')
const searchBar1 = document.getElementById('searchInput')

const allVideos = [];



fetch("http://localhost:8000/video/viewAll/api").
then(response => response.json()).
then(data => {
    for (let video of data) {
        allVideos.push(video);
    }
})

console.log(allVideos);

searchBar1.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar1.value.toLowerCase();
    let filteredVideos = allVideos.filter(video => {
        return video.videoName.toLowerCase().includes(searchingCharacters)

    });
    displayVideos(filteredVideos);
})


const displayVideos = (videos) => {
    videosList.innerHTML = videos
        .map((a) => {
            return `
 <div class="col-md-3" >
                 <div class="card h-100">
                <div class="embed-responsive embed-responsive-16by9">
                                    <video width="320" height="240" controls controlsList="nodownload">
                                        <source src="${a.videoUrl}" type="video/mp4">
                                    </video>
                                </div>
                 <div class="card-body">
                                    <h4 class="card-title">${a.videoName}</h4>                                 
                                    <p class="card-text" >${a.description}</p>
                 </div>
                   
                    <div class="d-flex justify-content-between align-items-center">

                        <div class="btn-group">
                            <a href="/video/view/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">Детайли</a>
                        </div>
                                         
                      
                    </div>
                
            </div>
            </div>`
        })
        .join('');

}
