<div>
    <form class="form-horizontal">
        <div class="form-group">
            <label for="currentUser" class="col-sm-2 control-label">currentUser</label>

            <div class="col-sm-10">
                <span id="currentUser" class="form-control">${currentUser?.getClass()?:'null'}</span>
            </div>
        </div>
        <div class="form-group">
            <label for="principal" class="col-sm-2 control-label">principal</label>

            <div class="col-sm-10">
                <span id="principal" class="form-control">${principal?.getClass()?:'null'}</span>
            </div>
        </div>
        <div class="form-group">
            <label for="authentication" class="col-sm-2 control-label">authentication</label>

            <div class="col-sm-10">
                <span id="authentication" class="form-control">${authentication?.getClass()?:'null'}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="roles" class="col-sm-2 control-label">authorities</label>

            <div class="col-sm-10">
                <span id="roles" class="form-control">${authorities.join(', ')}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="authenticated" class="col-sm-2 control-label">authenticated</label>

            <div class="col-sm-10">
                <span id="authenticated" class="form-control">${authenticated}</span>
            </div>
        </div>
    </form>
</div>