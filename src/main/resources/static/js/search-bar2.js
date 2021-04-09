const lessonsList = document.getElementById('lessonsList')
const searchBar2 = document.getElementById('searchInput')

const allLessons = [];



fetch("https://localhost:8000/lesson/viewAll/api").
then(response => response.json()).
then(data => {
    for (let lesson of data) {
        allLessons.push(lesson);
    }
})

console.log(allLessons);

searchBar2.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar2.value.toLowerCase();
    let filteredLessons = allLessons.filter(lesson => {
        return lesson.chapterName.toLowerCase().includes(searchingCharacters)

    });
    displayLessons(filteredLessons);
})


const displayLessons = (lessons) => {
    lessonsList.innerHTML = lessons
        .map((a) => {
            return `
 <div class="col-md-3" >
                 <div class="card h-100">
                <div class="embed-responsive embed-responsive-16by9">
                                    <video width="320" height="240" controls controlsList="nodownload">
                                        <source src="${a.lessonUrl}" type="video/mp4">
                                    </video>
                                </div>
                 <div class="card-body">
                                    <h4 class="card-title">${a.lessonName}</h4>                                 
                                    <p class="card-text" >${a.chapterName}</p>
                 </div>
                   
                    <div class="d-flex justify-content-between align-items-center">

                        <div class="btn-group">
                            <a href="/lesson/view/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">Детайли</a>
                        </div>                                        
                      
                    </div>
                
            </div>
            </div>`
        })
        .join('');

}
