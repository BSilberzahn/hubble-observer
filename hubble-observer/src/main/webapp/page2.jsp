<jsp:include page="static/html/header.html" />

<div class="container">

	<div class="panel panel-default">
		<div class="panel-body">
			<p>Vous pouvez choisir ci-dessous les fiches de contr�les
				auxquelles vous souhaitez vous abonner. Elles sont class�es par
				domaines : les prescriptions Hubble sous l'onglet ��SAV�� et les
				points de contr�les techniques sous l'onglet ��DTech��. En cochant
				la case � gauche des jalons, vous recevrez l'ensemble des fiches de
				contr�le qui les concernent aux dates renseign�es pour ces jalons,
				dans les champs de droite</p>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<h3 class="col-md-offset-3 col-md-3">SAV</h3>
				<h3 class="col-md-offset-3 col-md-3">DTech</h3>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<div id="savAccordion"></div>
		</div>

		<div class="col-md-6">
			<div id="dtechAccordion"></div>
		</div>
	</div>
</div>
<jsp:include page="static/html/footer.html" />
<script type="text/javascript" src="static/js/page2.js"></script>