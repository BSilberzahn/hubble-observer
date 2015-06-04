<%@ page pageEncoding="UTF-8"%>
<jsp:include page="static/html/header.html" />

<div class="container">

	<div class="panel panel-default">
		<div class="panel-body">Merci d'utiliser HubbleReminder ! Vos
			choix ont été enregistrés et un email contenant le lien
			d'installation de votre calendrier de projet a été envoyé aux
			adresses suivantes :</div>
			<div id="list" class="panel-body"></div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<p>
				Pour installer le calendrier HubbleReminder sur votre espace
				Outlook, il vous suffit de cliquer sur le lien dans le mail que nous
				venons de vous envoyer.<br /> Procédure d'installation:
			</p>
			<ul>
				<li>Faire un clic gauche sur le bandeau rouge en haut du mail
					afin d'« Ajouter l'expéditeur à la liste des expéditeurs
					approuvés ».</li>
				<li>Cliquer sur le lien</li>
				<li>Ouvrir le lien .ics avec « Microsoft Office Outlook »,
					cliquer sur OK</li>
				<li>En retournant sur Outlook, cliquer sur OUI à la question
					« Voulez-vous ajouter le calendrier ? »</li>
			</ul>
			<p>Le calendrier est maintenant installé dans vos calendriers.
				Vous pouvez le consulter en cliquant sur l'onglet « Calendrier »
				dans le bandeau de gauche. Il s'agit du calendrier HubbleReminder -
				Nom de votre Projet</p>
		</div>
	</div>
</div>

<jsp:include page="static/html/footer.html" />
<script type="text/javascript" src="static/js/page3.js"></script>