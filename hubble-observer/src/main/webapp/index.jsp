<jsp:include page="static/html/header.html" />

<div class="jumbotron">
	<div class="container starter-template">
		<h3>Cr�er un nouveau projet HubbleReminder</h3>
		<p class="lead">
		<form class="form-inline">
			<div class="form-group">
				<label for="projectName">Nom du projet </label> <input type="text"
					id="projectName" class="form-control" placeholder="nom"> <a
					class="btn btn-lg btn-primary"  id="validBtn" 
					role="button">&raquo; Valider ! &laquo;</a>
					<!-- href="page2.html" -->
			</div>
		</form>
		</p>
	</div>
</div>
<p>
	<a href="webapi/myresource">Jersey resource</a>
<div class="container">
	<div class="row">
		<div class="col-md-6">
			<h2>HubbleReminder</h2>
			<p>
				Un lien pour installer votre nouveau calendrier de projet vous sera
				envoy� par email aux adresses renseign�es ci-dessus. Ce calendrier
				Outlook aura pour nom: <b>HubbleReminder - Nom du projet</b>. Vous
				pourrez y retrouver les jalons aux dates compl�t�es en page
				suivante.
			</p>
			<p>Bonne continuation sur HubbleReminder !</p>
		</div>
		<div class="col-md-6">
			<h2>Ajouter des Emails</h2>
			<div class="container">
				<div class="row">
					<form>
						<div class="form-group">
							<div class="col-md-1">
								<label for="inputEmail">Email : </label>
							</div>
							<div class="col-md-4">
								<input type="email" class="form-control" id="inputEmail"
									placeholder="jane.doe@bouygues-construction.com">
							</div>
							<div class="col-md-1">
								<button type="button" class="btn btn-primary" id="emailBtn">ajouter</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<ul id="emailList" class="list-group">
			</ul>
		</div>
	</div>
</div>

<jsp:include page="static/html/footer.html" />
<script type="text/javascript" src="static/js/page1.js"></script>