document.addEventListener('DOMContentLoaded', function () {
  document.querySelectorAll('.flip-card').forEach(function (card) {
    const btnStart = card.querySelector('.btn-start');
    const btnEnd = card.querySelector('.btn-end');
    const ratingBlock = card.querySelector('.rating-block');

    card.addEventListener('click', function () {
      card.classList.toggle('flipped');
    });

    if (btnStart) {
      btnStart.addEventListener('click', function (event) {
        event.stopPropagation();
        btnStart.classList.toggle('active');
        btnStart.textContent = btnStart.classList.contains('active') ? 'Продовжити' : 'Старт';
      });
    }

    if (btnEnd) {
      btnEnd.addEventListener('click', function (event) {
        event.stopPropagation();
        if (ratingBlock) {
          ratingBlock.classList.add('visible');
        }
      });
    }
  });
});
